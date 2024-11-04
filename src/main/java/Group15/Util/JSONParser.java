package Group15.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JSONParser {

    public static <T> void saveObjectsAsJSON(String filePath, T[] objectsToSave) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            jsonMapper.writeValue(file, objectsToSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T[] loadObjectsFromJSON(String filePath, Class<T[]> objectType) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write("[{}]".getBytes());
            }
            return jsonMapper.readValue(new File(filePath), objectType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
