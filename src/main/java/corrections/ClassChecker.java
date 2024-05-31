package corrections;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassChecker {

    private ClassStructure cs;
    private List<String> errors = new ArrayList<>();
    private Boolean validated = false;
    private Boolean valid = false;

    public ClassChecker(ClassStructure c) {
        cs = c;
    }

    public boolean isValid() {
        if(validated) return valid;
        return validate();
    }

    private boolean validate() {
        errors = new ArrayList<>();

        Boolean validationResult = true;

        try {
            // Load the class
            Class<?> clazz = Class.forName(cs.getClassName());

            // Validate each method signature
            for (MethodSignature methodSignature : cs.getMethodSignatures()) {

                Method method;

                // Convert parameter types from List<Class<?>> to array
                Class<?>[] parameterTypesArray = this.parameterTypesToArray(methodSignature.getParameterTypes());

                try {
                    method = clazz.getDeclaredMethod(methodSignature.getMethodName(), parameterTypesArray);
                } catch (NoSuchMethodException e) {
                    errors.add(cs.getClassName() + ": Method " + getMethodSignatureString(methodSignature) + ": " + methodSignature.getReturnType() + " not found.");
                    validationResult = false;
                    continue;
                }

                // Check if the return type matches
                if (!method.getReturnType().equals(methodSignature.getReturnType())) {
                    errors.add(cs.getClassName() + ": Method " + methodSignature.getMethodName() + " has incorrect return type.");
                    validationResult = false;
                }

                // Check if the access modifier matches
                if (!modifierMatches(method.getModifiers(), methodSignature.getAccessModifier())) {
                    errors.add(cs.getClassName() + ": Method " + methodSignature.getMethodName() + " has incorrect access modifier.");
                    validationResult = false;
                }
            }

        } catch (ClassNotFoundException e) {
            errors.add("Class " + cs.getClassName() + " does not exist.");
            validationResult = false;
        } catch (SecurityException e) {
            errors.add("Security exception encountered: " + e.getMessage());
            validationResult = false;
        }

        validated = true;
        valid = validationResult;
        return validationResult;
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

    private String getMethodSignatureString(MethodSignature methodSignature) {
        StringBuilder signature = new StringBuilder();
        signature.append(methodSignature.getMethodName());
        signature.append("(");

        List<Class<?>> parameterTypes = methodSignature.getParameterTypes();
        if (parameterTypes != null) {
            for (int i = 0; i < parameterTypes.size(); i++) {
                if (i > 0) {
                    signature.append(", ");
                }
                signature.append(parameterTypes.get(i).getSimpleName());
            }
        }

        signature.append(")");
        return signature.toString();
    }

    public List<String> getErrors() {
        if(!validated) validate();
        return errors;
    }

    private Class<?>[] parameterTypesToArray(List<Class<?>> parameterTypes) {
        if(parameterTypes == null) return new Class<?>[0];
        return parameterTypes.toArray(new Class<?>[0]);
    }
}
