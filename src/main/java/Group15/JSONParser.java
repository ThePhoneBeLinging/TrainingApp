package Group15;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JSONParser
{

    public static <T> void saveObjectAsJSON(String filePath, T[] objectsToSave)
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        try
        {
            jsonMapper.writeValue(new File(filePath), objectsToSave);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static <T> T[] loadObjectsFromJSON(String filePath, Class<T[]> objectType)
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        try
        {
            return jsonMapper.readValue(new File(filePath), objectType);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
