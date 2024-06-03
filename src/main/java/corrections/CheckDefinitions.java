package corrections;

import java.util.ArrayList;

public class CheckDefinitions {

  public static void main(String[] args) {

    if (args.length == 0) {
      System.err.println("No directory path provided.");
      System.exit(1);
    }

    String path = args[0];

    try {
      ArrayList<String> errors;
      DirectoryChecker dc = new DirectoryChecker(path);

      if (!dc.isValid()) {
        errors = (ArrayList<String>) dc.getErrors();
        for (String error : errors) {
          System.err.println(error);
        }
        System.exit(1);
      }
    } catch (Exception e) {
      System.err.println("An error occurred while checking definitions.");
      // e.printStackTrace(System.err);
    }
  }
}
