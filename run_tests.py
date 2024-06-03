import os
import subprocess
import json
import shutil

import xml.etree.ElementTree as ET
import re


DEBUG = 'N'

def log(message):
    if DEBUG == 'S':
        print(message)

# Set the classpath to include all dependencies and compiled classes
classpath = ':'.join(
    [os.path.join(dp, f) for dp, dn, filenames in os.walk('/app/dependency') for f in filenames if f.endswith('.jar')]
) + ':/app/classes:/app/test-classes:/app'

log("Copying the student's code...")

# Copy the student's code to the correct directory
PACKAGE = 'empleados'
FILE_PATH = f'/app/classes/{PACKAGE}'
os.makedirs(FILE_PATH, exist_ok=True)
result = subprocess.run(['cp', '/tmp/exercise', f'{FILE_PATH}/EmpleadoBR.java'], check=True)
if result.returncode != 0:
    error_message = {
        "success": False,
        "error": "Failed to copy the student's code"
    }
    print(json.dumps(error_message))
    # Exit with an error code, the correction failed
    exit(1)

log("Compiling the student's code...")

# Compile the student's code, replacing the stubs
compile_error_file = '/tmp/compile_errors.txt'
compile_command = [
    'javac', '-cp', classpath, f'{FILE_PATH}/EmpleadoBR.java',
    '-sourcepath', '/app/classes'
]

compile_result = subprocess.run(compile_command, stderr=subprocess.PIPE, text=True)
if compile_result.returncode != 0:
    compile_errors = compile_result.stderr.replace(f'{FILE_PATH}/', '')

    error_message = {
        "success": False,
        "error": f"Compilation failed: {compile_errors}"
    }
    print(json.dumps(error_message))

    # Exit with an OK code, since the container succeeded but the correction failed
    exit(0)

log("Compilation successful!")
log("Running the tests...")

# Run the JUnit Console Launcher to execute the tests
REPORTS_DIR = 'test-reports'
REPORTS_FILE = f'{REPORTS_DIR}/TEST-junit-jupiter.xml'
RESULT_FILE = '/tmp/test_results.txt'
ERROR_FILE = '/tmp/test_errors.txt'

# Remove the reports directory if it exists
shutil.rmtree(REPORTS_DIR, ignore_errors=True)

test_command = [
    'java', '-jar', 'junit-platform-console-standalone.jar',
    '--class-path', classpath,
    '--scan-class-path',
    '--details=tree',
    f'--reports-dir={REPORTS_DIR}',
]

# We don't need the stdout, because it will generate report files
with open(os.devnull, 'w') as devnull:
    run_result = subprocess.run(test_command, stdout=devnull, stderr=subprocess.PIPE, text=True)


# If junit console launcher returns 0, all tests passed
if run_result.returncode == 0:
    log('All tests passed')
    success_message = {
        'success': True,
        'grade': 10,
        'comments': [ 'The code is working properly' ]
    }
    print(json.dumps(success_message))
    # Exit with an OK code, the container succeeded and the correction passed
    exit(0)

# The console launcher failed. We need to tell apart if the tests failed or the execution failed
# Content in stderr means that the execution failed
if run_result.stderr:
    log(f'Failed to run the tests: {run_result.stderr}')
    error_message = {
        'success': False,
        'error': f'Failed to run the tests: {run_result.stderr}'
    }
    print(json.dumps(error_message))
    # The correction failed, probably because of something wrong in the container
    exit(1)

# If we reach this point, everything went OK but some tests failed
# We will parse the JUnit XML file to get the failed tests
# and return the results as a success correction with errors
log('Some tests failed, parsing the results...')

def get_failed_tests(file_path):
    tree = ET.parse(file_path)
    root = tree.getroot()

    def extract_display_name(text):
        pattern = re.compile(r"display-name: (.+)")
        match = pattern.search(text)
        return match.group(1) if match else None

    failure_errors = root.findall('.//testcase[failure]/system-out')
    failure_descriptions = map(lambda x: extract_display_name(x.text), failure_errors)
    return list(set(failure_descriptions))


# Create the JSON output
success_message = {
    'success': True,
    'grade': 0,
    'comments': get_failed_tests(REPORTS_FILE)
}
print(json.dumps(success_message))
