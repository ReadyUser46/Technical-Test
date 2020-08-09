import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Pet;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Tarea3 {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String baseUrl = "https://petstore.swagger.io/v2";
        JSONObject newUser = new JSONObject();

        // Task3.1 --> Create user by http request and retrieve user data
        //Request call1 = onPostUser(baseUrl, newUser);
        //Request call2 = onGetUser(baseUrl, newUser.getString("username"));
        Request call3 = onGetPetFindByStatus(baseUrl, "sold");

        try (Response response = client.newCall(call3).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get respone body
            String responseString = response.body().string();
            System.out.println("\nResponse (json): " + responseString);
            System.out.println(responseString);

            List<Pet> petList = deserializeResponse(responseString);
            Map<Long, String> petMapTask3 = onCreateHastMapFromPetList(petList);

            // Task3.2 --> List sold pet names with format {id, name} by using a HashMap object
            System.out.println("\nDumping hashMap objects for sold pets --> (id, name) ");
            System.out.println(petMapTask3);
            System.out.println("\n" +
                    "Beauty hashmap output:");
            for (Map.Entry m : petMapTask3.entrySet()) {
                System.out.println(String.format("{id: %s , name: %s}", m.getKey(), m.getValue()));
            }

            // Task3.3 --> List sold pet names with format {id, name} by using a HashMap object
            PetTools petTools = new PetTools(petMapTask3);
            petTools.samePetNames();
        }
    }

    private static Request onGetUser(String baseUrl, String user) {
        System.out.println("Fetching data for user: " + user);
        return new Request.Builder()
                .url(baseUrl + "/user/" + user)
                .build();
    }

    private static Request onPostUser(String baseUrl, JSONObject newUser) {

        newUser.put("id", 5);
        newUser.put("username", "user377");
        newUser.put("firstName", "Joker377");
        newUser.put("lastName", "Joker378");
        newUser.put("email", "joker@joker.com");
        newUser.put("password", "user377");
        newUser.put("phone", "377");
        newUser.put("userStatus", 0);

        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON, newUser.toString());

        System.out.println("Creating new user...");

        return new Request.Builder()
                .url(baseUrl + "/user/")
                .post(body)
                .build();
    }

    private static Request onGetPetFindByStatus(String baseUrl, String status) {

        System.out.println("\nFinding pets by status: " + status);

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("petstore.swagger.io")
                .addPathSegment("v2")
                .addPathSegment("pet")
                .addPathSegment("findByStatus")
                .addQueryParameter("status", status)
                .build();

        return new Request.Builder()
                .url(url)
                .build();
    }

    private static List<Pet> deserializeResponse(String respose) {

        Gson gson = new Gson();

        Type petType = new TypeToken<List<Pet>>() {
        }.getType();

        return gson.fromJson(respose, petType);
    }

    private static Map<Long, String> onCreateHastMapFromPetList(List<Pet> petList) {
        Map<Long, String> task3Map = new LinkedHashMap<>();

        for (Pet pet : petList) {
            task3Map.put(pet.getPetId(), pet.getPetName());
        }
        return task3Map;
    }

}
