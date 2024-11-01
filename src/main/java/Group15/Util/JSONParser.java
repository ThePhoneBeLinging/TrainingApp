package Group15.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONParser
{

    public static <T> void saveObjectsAsJSON(String filePath, T[] objectsToSave)
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
