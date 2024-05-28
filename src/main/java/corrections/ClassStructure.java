package corrections;

import java.util.List;

public class ClassStructure {
    private String className;
    private List<MethodSignature> methodSignatures;

    public ClassStructure(String className, List<MethodSignature> methodSignatures) {
        this.className = className;
        this.methodSignatures = methodSignatures;
    }

    public String getClassName() {
        return className;
    }

    public List<MethodSignature> getMethodSignatures() {
        return methodSignatures;
    }
}
