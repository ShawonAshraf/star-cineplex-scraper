package utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONHelper {
    public static String parseObjectToJSONString(Object obj) {
        var mapper = new ObjectMapper();

        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return jsonString;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
