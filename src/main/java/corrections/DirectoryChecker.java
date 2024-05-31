package corrections;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/*
 * This class checks a directory for .yaml files and processes them using the ClassChecker.
 */
public class DirectoryChecker {

    private String path;
    private List<String> errors = new ArrayList<>();

    public DirectoryChecker(String path) {
        this.path = path;
    }

    private File[] getYamlFiles(File directory) {
        // Filter to get only .yaml files
        FilenameFilter yamlFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".yaml");
            }
        };
        return directory.listFiles(yamlFilter);
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean isValid() {
        // Clear errors
        errors = new ArrayList<>();

        File directory = new File(path);

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a directory.");
        }

        File[] yamlFiles = this.getYamlFiles(directory);

        if (yamlFiles == null || yamlFiles.length == 0) {
            // No files, no errors
            return true;
        }

        // Process each .yaml file
        boolean result = true;

        for (File yamlFile : yamlFiles) {
            ClassChecker cc = new ClassChecker(YamlLoader.loadClassStructure(yamlFile.getAbsolutePath()));
            errors.addAll(cc.getErrors());
            result = result && cc.isValid();
        }
        return result;
    }
}
