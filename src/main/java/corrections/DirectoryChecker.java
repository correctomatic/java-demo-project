package corrections;

import java.io.File;
import java.io.FilenameFilter;

/*
 * This class checks a directory for .yaml files and processes them using the ClassChecker.
 */
public class DirectoryChecker {

    public static void main(String directoryPath ) {
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("The provided path is not a directory.");
            return;
        }

        // Filter to get only .yaml files
        FilenameFilter yamlFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".yaml");
            }
        };

        // Get the list of .yaml files in the directory
        File[] yamlFiles = directory.listFiles(yamlFilter);

        if (yamlFiles == null || yamlFiles.length == 0) {
            System.out.println("No .yaml files found in the directory.");
            return;
        }

        // Process each .yaml file
        for (File yamlFile : yamlFiles) {
            // Banana.papaya(yamlFile.getAbsolutePath());
        }
    }
}
