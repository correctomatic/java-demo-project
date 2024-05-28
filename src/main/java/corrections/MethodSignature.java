package corrections;

import java.util.List;

public class MethodSignature {
    private String methodName;
    private List<Class<?>> parameterTypes;
    private Class<?> returnType;
    private String accessModifier;

    public MethodSignature(String methodName, List<Class<?>> parameterTypes, Class<?> returnType, String accessModifier) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
        this.accessModifier = accessModifier;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<Class<?>> getParameterTypes() {
        return parameterTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public String getAccessModifier() {
        return accessModifier;
    }
}
