package corrections;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodSignatureDeserializer extends JsonDeserializer<MethodSignature> {
    @Override
    public MethodSignature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String methodName = node.get("methodName").asText();
        JsonNode paramTypesNode = node.get("parameterTypes");
        List<Class<?>> parameterTypes = new ArrayList<>();
        for (JsonNode typeNode : paramTypesNode) {
            try {
                parameterTypes.add(Class.forName(typeNode.asText()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        String returnTypeStr = node.get("returnType").asText();
        Class<?> returnType;
        try {
            returnType = Class.forName(returnTypeStr);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String accessModifier = node.get("accessModifier").asText();

        return new MethodSignature(methodName, parameterTypes, returnType, accessModifier);
    }
}
