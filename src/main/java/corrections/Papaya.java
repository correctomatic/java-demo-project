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

  private static ClassStructure byHand() {
    // Example usage
    List<MethodSignature> methodSignatures = List.of(
        new MethodSignature("banana", List.of(String.class, int.class), int.class, "public"),
        new MethodSignature("papaya", List.of(int.class), void.class, "protected")
    );

    ClassStructure classStructure = new ClassStructure("empleados.Banana", methodSignatures);
    return classStructure;
  }


  private static ClassStructure fromFile(String filename) throws IOException {
    ClassStructure c = YamlLoader.loadClassStructure(filename);
    return c;
  }

  public static void main(String[] args) throws Exception{
    ClassStructure c;
    boolean isValid;

    ClassChecker cc;
    ArrayList<String> errors;

    // c = byHand();
    // // printStructure(c);
    // cc = new ClassChecker(c);
    // isValid = cc.isValid();
    // errors = (ArrayList<String>) cc.getErrors();
    // System.out.println("Validation result: " + isValid);
    // for (String error : errors) {
    //   System.out.println(error);
    // }

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
