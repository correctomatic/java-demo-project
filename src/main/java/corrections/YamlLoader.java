package corrections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class YamlLoader {
    public static ClassStructure loadClassStructure(InputStream inputStream) throws IOException{
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        // String filename = "/home/alvaro/Software/correctomatic/java-demo-project/foo.yaml";
        String filename = "/home/alvaro/Software/correctomatic/java-demo-project/banana_definition.yaml";
        File file = new File(filename);
        // Foo f = mapper.readValue(file, Foo.class);
        ClassStructure c = mapper.readValue(file, ClassStructure.class);
        System.out.println(c);

        // SimpleModule module = new SimpleModule();
        // module.addDeserializer(MethodSignature.class, new MethodSignatureDeserializer());
        // mapper.registerModule(module);

        try {
            return mapper.readValue(inputStream, ClassStructure.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load class structure from YAML", e);
        }
    }
}
