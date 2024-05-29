package corrections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;

public class YamlLoader {
    public static ClassStructure loadClassStructure(InputStream inputStream) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(MethodSignature.class, new MethodSignatureDeserializer());
        mapper.registerModule(module);

        try {
            return mapper.readValue(inputStream, ClassStructure.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load class structure from YAML", e);
        }
    }
}
