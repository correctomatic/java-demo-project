package corrections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class YamlLoader {
    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static ClassStructure loadClassStructure(String filename){
    try {
        File file = new File(filename);
        ClassStructure c = mapper.readValue(file, ClassStructure.class);
        return c;
    } catch (Exception e) {
        throw new RuntimeException("Failed to load class structure from YAML", e);
    }
    }
}
