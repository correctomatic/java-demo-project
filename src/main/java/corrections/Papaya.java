package corrections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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

    try (FileInputStream inputStream = new FileInputStream(filename)) {
      ClassStructure c = YamlLoader.loadClassStructure(inputStream);
      return c;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String[] args) throws Exception{
    ClassStructure c;
    boolean isValid;

    // c = byHand();
    // printStructure(c);
    // isValid = ClassChecker.validateClassStructure(c);
    // System.out.println("Validation result: " + isValid);

    String file = "/home/alvaro/Software/correctomatic/java-demo-project/banana_definition.yaml";
    c = fromFile(file);
    printStructure(c);
    isValid = ClassChecker.validateClassStructure(c);
    System.out.println("Validation result: " + isValid);
  }

}
