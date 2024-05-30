package corrections;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
}
