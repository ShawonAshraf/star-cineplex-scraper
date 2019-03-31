package utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

    public static void writeJSONStringToFile(String jsonString, String fileName) {
        try (FileWriter file = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(file)
        ) {
            out.write(jsonString);
            System.out.println("JSON has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
