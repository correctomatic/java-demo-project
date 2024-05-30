package corrections;

import java.util.List;

public class ClassStructure {
    private String className;
    private List<MethodSignature> methodSignatures;

    public ClassStructure() {
    }

    public ClassStructure(String className, List<MethodSignature> methodSignatures) {
        this.className = className;
        this.methodSignatures = methodSignatures;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public List<MethodSignature> getMethodSignatures() {
        return methodSignatures;
    }
    public void setMethodSignatures(List<MethodSignature> methodSignatures) {
        this.methodSignatures = methodSignatures;
    }
}
