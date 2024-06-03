
export CLASSPATH=$CLASSPATH:/home/appnetix/LOG4J_HOME/

 docker run --rm -ti --mount type=bind,src=./ejemplos/ejemplo1,dst=/app ibmjava:8-sdk


docker build -t correction-test-java .


### Notes, commands to run the container

docker run --rm \
  -v `pwd`/exercises/EmpleadoBR.java:/tmp/exercise \
  correction-test-java

docker run --rm -ti -v `pwd`/src:/app/src correction-test-java /bin/sh

docker run --rm -ti \
  -v `pwd`/exercises/EmpleadoBR.java:/tmp/exercise \
  correction-test-java /bin/sh


docker run --rm -ti \
  -v `pwd`/exercises/EmpleadoBR_compile_error.java:/tmp/exercise \
  -v `pwd`/run_tests.sh:/app/run_tests.sh \
  correction-test-java


docker run --rm -ti \
  -v `pwd`/exercises/EmpleadoBR_errors.java:/tmp/exercise \
  -v `pwd`/run_tests.sh:/app/run_tests.sh \
  -v `pwd`/run_tests.py:/app/run_tests.py \
  -v `pwd`/test-reports:/app/test-reports \
  correction-test-java


docker run --rm -ti \
  -v `pwd`/exercises/EmpleadoBR_errors.java:/tmp/exercise \
  -v `pwd`/run_tests.sh:/app/run_tests.sh \
  -v `pwd`/run_tests.py:/app/run_tests.py \
  -v `pwd`/test-reports:/app/test-reports \
  correction-test-java /bin/bash



# Run Tests: Run your tests using the mvn test command. S

Optimize size:
https://whitfin.io/blog/speeding-up-maven-docker-builds/


mvn archetype:generate -DgroupId=com.example -DartifactId=my-project -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


Surefire report schema:
https://maven.apache.org/surefire/maven-surefire-plugin/xsd/surefire-test-report.xsd



## Container sizes


two stages openjdk:11-jdk-slim                          - 428MB
two stages bellsoft/liberica-openjdk-alpine-musl:22-cds - 151MB
two stages bellsoft... + python                         - 193MB



