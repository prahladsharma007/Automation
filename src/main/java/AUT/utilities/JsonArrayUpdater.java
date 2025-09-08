package AUT.utilities;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonArrayUpdater {

    public static void updateArrayValue(String newData, String filePath){
        //JsonElement newData = JsonParser.parseString(jsonResponse);
        // Step 1: Read the existing JSON file
        JsonArray jsonArray = new JsonArray();
        try (FileReader reader = new FileReader("response.json")) {
            // Check if the file is not empty before parsing
            if (reader.ready()) {
                jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 3: Add the new data to the existing JSON array
        jsonArray.add(newData);

        // Step 4: Write the updated JSON array back to the file
        try (FileWriter fileWriter = new FileWriter("response.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonArray, fileWriter);
            System.out.println("Successfully appended new data to the JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

