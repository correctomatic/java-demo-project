package corrections;

import java.util.List;

public class MethodSignature {
    private String methodName;
    private List<Class<?>> parameterTypes;
    private Class<?> returnType;

    public MethodSignature(String methodName, List<Class<?>> parameterTypes, Class<?> returnType) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
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
}
