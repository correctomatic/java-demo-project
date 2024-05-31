package corrections;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class Papaya {

   private static void printStructure(ClassStructure c) {
    System.out.println("Class Name: " + c.getClassName());
    for (MethodSignature method : c.getMethodSignatures()) {
      System.out.println("Method Name: " + method.getMethodName());
      System.out.println("Parameter Types: " + method.getParameterTypes());
      System.out.println("Return Type: " + method.getReturnType());
      System.out.println("Access Modifier: " + method.getAccessModifier());
    }
  }

  private static ClassStructure fromFile(String filename) throws IOException {
    ClassStructure c = YamlLoader.loadClassStructure(filename);
    return c;
  }

  public static void main(String[] args) {
    ArrayList<String> errors;
    String path = "./definitions";
    DirectoryChecker dc = new DirectoryChecker(path);
    boolean isValid = dc.isValid();
    errors = (ArrayList<String>) dc.getErrors();
    System.out.println("Validation result: " + isValid);
    for (String error : errors) {
      System.out.println(error);
    }
  }

  public static void mainLALALA(String[] args) throws Exception{
    ClassStructure c;
    boolean isValid;

    ClassChecker cc;
    ArrayList<String> errors;

    String file = "/home/alvaro/Software/correctomatic/java-demo-project/banana_definition.yaml";
    c = fromFile(file);
    // printStructure(c);
    cc = new ClassChecker(c);
    isValid = cc.isValid();
    errors = (ArrayList<String>) cc.getErrors();
    System.out.println("Validation result: " + isValid);
    for (String error : errors) {
      System.out.println(error);
    }
  }

}
