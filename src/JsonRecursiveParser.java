import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonRecursiveParser {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Load JSON from file or string
        JsonNode rootNode = mapper.readTree(new File("data.json")); // or use readTree(String json)

        traverseJson(rootNode, "");
    }

    public static void traverseJson(JsonNode node, String path) {
        if (node.isObject()) {
//            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
//            while (fields.hasNext()) {
//                Map.Entry<String, JsonNode> entry = fields.next();
            for (Map.Entry<String, JsonNode> entry : node.properties())
            {
                String currentPath = path.isEmpty() ? entry.getKey() : path + "." + entry.getKey();
                traverseJson(entry.getValue(), currentPath);
            }
        } else if (node.isArray()) {
            int index = 0;
            for (JsonNode item : node) {
                String currentPath = path + "[" + index + "]";
                traverseJson(item, currentPath);
                index++;
            }
        } else {
            // Leaf node
            System.out.println(path + " : " + node.asText());
        }
    }
}
