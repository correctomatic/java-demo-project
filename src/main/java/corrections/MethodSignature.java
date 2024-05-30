package corrections;

import java.util.List;

public class MethodSignature {
    private String methodName;
    private List<Class<?>> parameterTypes;
    private Class<?> returnType;
    private String accessModifier;

    public MethodSignature() {
    }

    public MethodSignature(String methodName, List<Class<?>> parameterTypes, Class<?> returnType, String accessModifier) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
        this.accessModifier = accessModifier;
    }

    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Class<?>> getParameterTypes() {
        return parameterTypes;
    }
    public void setParameterTypes(List<Class<?>> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }
    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public String getAccessModifier() {
        return accessModifier;
    }
    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }
}
