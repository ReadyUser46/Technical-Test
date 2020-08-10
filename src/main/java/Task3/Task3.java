package Task3;

import Task3.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Task3.models.Pet;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    // Member variables
    private final OkHttpClient client;
    private final String baseUrl;

    // Constructor
    public Task3() {
        client = new OkHttpClient();
        baseUrl = "https://petstore.swagger.io/v2";
    }

    // Methods
    private Response executeHttpRequest(Request call) throws IOException {
        return client.newCall(call).execute();
    }

    public void onPostUser(JSONObject newUser) {
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON, newUser.toString());

        System.out.println("Creating new user...");

        Request postRequest = new Request.Builder()
                .url(baseUrl + "/user/")
                .post(body)
                .build();

        try {
            Response response = executeHttpRequest(postRequest);
            if (response.isSuccessful()) {
                System.out.println("New user registration successfull");
            } else {
                System.out.println("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onGetUser(String user) {

        System.out.printf("Retrieving data for user \"%s\"\n", user);
        Request getRequest = new Request.Builder()
                .url(baseUrl + "/user/" + user)
                .build();

        try {
            Response response = executeHttpRequest(getRequest);
            if (response.isSuccessful() && response.body() != null) {

                User retrievedUser = deserializeUserResponse(response.body().string());
                System.out.printf("  Name: %s\n  "
                                + "First Name: %s\n  "
                                + "Last name: %s\n  "
                                + "Email: %s\n  "
                                + "Password: %s\n  "
                                + "Phone: %s\n  "
                                + "User Status: %s\n",
                        user, retrievedUser.getName(), retrievedUser.getFirstName(),
                        retrievedUser.getLastName(), retrievedUser.getEmail(),
                        retrievedUser.getPassword(), retrievedUser.getPhone(),
                        retrievedUser.getUserStatus());

            } else {
                System.out.println("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Long, String> onGetPetFindByStatus(String status) {

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("petstore.swagger.io")
                .addPathSegment("v2")
                .addPathSegment("pet")
                .addPathSegment("findByStatus")
                .addQueryParameter("status", status)
                .build();

        Request getRequest = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = executeHttpRequest(getRequest);
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Getting pets by the status " + status);
                String responseString = response.body().string();

                System.out.println("*Deserialization http response to Java Pet model --> done");
                List<Pet> petList = deserializePetResponse(responseString);
                Map<Long, String> tupleTask3 = onCreateHastMapFromPetList(petList);

                System.out.println("Listing pet names sold with format {id, name} by using a HashMap object");
                System.out.println(tupleTask3);
                System.out.println("\nPretty format hashmap output: ");
                for (Map.Entry<Long, String> m : tupleTask3.entrySet()) {
                    System.out.printf("{id: %s , name: %s}%n", m.getKey(), m.getValue());
                }
                return tupleTask3;
            } else {
                System.out.println("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onGetSamePetNames(Map<Long, String> petMapTask3) {
        System.out.println("Identifying how many pets have the same name...");
        PetTools petTools = new PetTools(petMapTask3);
        petTools.samePetNames();

        System.exit(0);
    }

    private List<Pet> deserializePetResponse(String response) {

        Gson gson = new Gson();

        Type petType = new TypeToken<List<Pet>>() {
        }.getType();

        return gson.fromJson(response, petType);
    }

    private User deserializeUserResponse(String response) {
        Gson gson = new Gson();

        //Type userType = new TypeToken<User>() {
        // }.getType();

        return gson.fromJson(response, User.class);
    }

    private Map<Long, String> onCreateHastMapFromPetList(List<Pet> petList) {
        Map<Long, String> task3Map = new LinkedHashMap<>();

        for (Pet pet : petList) {
            task3Map.put(pet.getPetId(), pet.getPetName());
        }
        return task3Map;
    }

}
