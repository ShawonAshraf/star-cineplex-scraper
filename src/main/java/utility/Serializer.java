package utility;

import java.io.*;

public class Serializer {
    public static void writeToFile(String filePath, Object obj) {
        try (FileOutputStream file = new FileOutputStream(filePath);
                ObjectOutputStream out = new ObjectOutputStream(file)
        ) {
            out.writeObject(obj);
            System.out.println("Object has been serialized to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object readFromFile(String filePath) {
        Object obj = null;

        try {
            InputStream in = Serializer.class.getClassLoader()
                    .getResourceAsStream(filePath);
            ObjectInputStream reader = new ObjectInputStream(in);

            obj = reader.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
