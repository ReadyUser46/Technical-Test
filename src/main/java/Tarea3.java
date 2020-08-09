import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class Tarea3 {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String baseUrl = "https://petstore.swagger.io/v2";

        //Request call = onGetUser(baseUrl, "user377");
        //Request call = onPostUser(baseUrl);
        Request call = onGetPetFindByStatus(baseUrl, "sold");


        try (Response response = client.newCall(call).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get respone body
            System.out.println("Response code: " +response.code());
            System.out.println(response.body().string());

            //listPetsByStatus(response.body().string(),"sold");
        }
    }

    private static Request onGetUser(String baseUrl, String user) {
        System.out.println("Fetching data for user: " + user);
        return new Request.Builder()
                .url(baseUrl + "/user/" + user)
                .build();
    }

    private static Request onPostUser(String baseUrl) {

        MediaType JSON = MediaType.parse("application/json;charset=utf-8");

        JSONObject bodyNewUser = new JSONObject();
        bodyNewUser.put("id", 5);
        bodyNewUser.put("username", "user377");
        bodyNewUser.put("firstName", "Joker377");
        bodyNewUser.put("lastName", "Joker378");
        bodyNewUser.put("email", "joker@joker.com");
        bodyNewUser.put("password", "user377");
        bodyNewUser.put("phone", "377");
        bodyNewUser.put("userStatus", 0);

        /*RequestBody body0 = new FormBody.Builder()
                .add("id", "5")
                .add("username", "test")
                .add("firstName", "test2")
                .add("lastName", "test4")
                .addEncoded("email", "test@adsfasdf.com")
                .add("password", "patapum")
                .add("phone", "654321987")
                .add("userStatus", "0")
                .build();*/

        RequestBody body = RequestBody.create(JSON, bodyNewUser.toString());

        System.out.println("Creating new user...");

        return new Request.Builder()
                .url(baseUrl + "/user/")
                .post(body)
                .build();
    }

    private static Request onGetPetFindByStatus(String baseUrl, String status) {

        System.out.println("Finding pets by status: " + status);

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("petstore.swagger.io")
                .addPathSegment("v2")
                .addPathSegment("pet")
                .addPathSegment("findByStatus")
                .addQueryParameter("status",status)
                .build();

        return new Request.Builder()
                .url(url)
                .build();
    }

    private static void listPetsByStatus(String respose, String status){

    }

}
