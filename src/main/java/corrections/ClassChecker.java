package corrections;

import java.lang.reflect.Method;
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
                Method method = clazz.getMethod(methodSignature.getMethodName(), parameterTypesArray);

                // Check if the return type matches
                if (!method.getReturnType().equals(methodSignature.getReturnType())) {
                    System.out.println("Method " + methodSignature.getMethodName() + " has incorrect return type.");
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

    public static void main(String[] args) {
        // Example usage
        List<MethodSignature> methodSignatures = List.of(
            new MethodSignature("banana", List.of(String.class, int.class), int.class),
            new MethodSignature("papaya", List.of(int.class), void.class)
        );

        ClassStructure classStructure = new ClassStructure("empleados.Banana", methodSignatures);

        boolean isValid = validateClassStructure(classStructure);
        System.out.println("Validation result: " + isValid);
    }
}
