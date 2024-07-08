package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    private static final String testDataPath ="src/test/resources/TestData/";

    //TODO: Reading data from Json File
    public static String getJsonData(String fileName, String field) throws FileNotFoundException //file + field
    {
        //Define object of file reader
        FileReader reader = new FileReader(testDataPath+fileName+".json");
        //Parse the JSON directly into a Json element
        JsonElement jsonElement = JsonParser.parseReader(reader);
        return  jsonElement.getAsJsonObject().get(field).getAsString();
    }
    //TODO: Reading data from properties file
    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(testDataPath+fileName+".properties"));
        return properties.getProperty(key);
    }
}
