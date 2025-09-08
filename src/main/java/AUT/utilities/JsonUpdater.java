package AUT.utilities;
import AUT.constants.CommonConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUpdater {
    public static void updateValue(String newKey, String newValue, String filePath){
        try {
// Step 1: Read the existing JSON file
            JsonObject jsonObject = new JsonObject();
            try (FileReader reader = new FileReader(filePath)) {
// Check if the file is not empty before parsing
                if (reader.ready()) {
                    jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                }
            } catch (IOException e) {
// Handle the case where the file doesn't exist or can't be read
                e.printStackTrace();
            }

// Step 2: Add the new key-value pair
            jsonObject.addProperty(newKey, newValue);

// Step 3: Write the updated JSON object back to the file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(jsonObject, fileWriter);
                System.out.println("Successfully appended new key-value pair to the JSON file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFromJson(String strKey) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode jsonNode = objMapper.readTree(new File(CommonConstants.projectDirectory+"/output.json"));
        return jsonNode.get(strKey).asText();
    }
}
