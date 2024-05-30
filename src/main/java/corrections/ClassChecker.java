package corrections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;


public class ClassChecker {

    public static boolean validateClassStructure(ClassStructure classStructure) {
        try {
            // Load the class
            Class<?> clazz = Class.forName(classStructure.getClassName());

            // Validate each method signature
            for (MethodSignature methodSignature : classStructure.getMethodSignatures()) {
                // Convert parameter types from List<Class<?>> to array
                Class<?>[] parameterTypesArray = methodSignature.getParameterTypes().toArray(new Class<?>[0]);

                // Check if the method exists with the specified parameter types
                Method method = clazz.getDeclaredMethod(methodSignature.getMethodName(), parameterTypesArray);

                // Check if the return type matches
                if (!method.getReturnType().equals(methodSignature.getReturnType())) {
                    System.out.println("Method " + methodSignature.getMethodName() + " has incorrect return type.");
                    return false;
                }

                // Check if the access modifier matches
                if (!modifierMatches(method.getModifiers(), methodSignature.getAccessModifier())) {
                    System.out.println("Method " + methodSignature.getMethodName() + " has incorrect access modifier.");
                    return false;
                }
            }

            System.out.println("Class " + classStructure.getClassName() + " matches the given structure.");
            return true;

        } catch (ClassNotFoundException e) {
            System.out.println("Class " + classStructure.getClassName() + " does not exist.");
            return false;
        } catch (NoSuchMethodException e) {
            System.out.println("Required method not found: " + e.getMessage());
            return false;
        } catch (SecurityException e) {
            System.out.println("Security exception encountered: " + e.getMessage());
            return false;
        }
    }

    private static boolean modifierMatches(int modifiers, String accessModifier) {
        switch (accessModifier.toLowerCase()) {
            case "public":
                return Modifier.isPublic(modifiers);
            case "protected":
                return Modifier.isProtected(modifiers);
            case "private":
                return Modifier.isPrivate(modifiers);
            case "default":
                return !Modifier.isPublic(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers);
            default:
                throw new IllegalArgumentException("Unknown access modifier: " + accessModifier);
        }
    }

    public static void main(String[] args) throws Exception{
        // Example usage
        List<MethodSignature> methodSignatures = List.of(
            new MethodSignature("banana", List.of(String.class, int.class), int.class, "public"),
            new MethodSignature("papaya", List.of(int.class), void.class, "protected")
        );

        ClassStructure classStructure = new ClassStructure("empleados.Banana", methodSignatures);

        String filename = "/home/alvaro/Software/correctomatic/java-demo-project/banana_definition.yaml";
        try (FileInputStream inputStream = new FileInputStream(filename)) {
            ClassStructure classStructure2 = YamlLoader.loadClassStructure(inputStream);
            System.out.println("Class Name: " + classStructure.getClassName());
            for (MethodSignature method : classStructure.getMethodSignatures()) {
                System.out.println("Method Name: " + method.getMethodName());
                System.out.println("Parameter Types: " + method.getParameterTypes());
                System.out.println("Return Type: " + method.getReturnType());
                System.out.println("Access Modifier: " + method.getAccessModifier());
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean isValid = validateClassStructure(classStructure);
        System.out.println("Validation result: " + isValid);
    }
}
