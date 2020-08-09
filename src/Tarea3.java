import okhttp3.*;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;


import java.io.IOException;

public class Tarea3 {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String baseUrl = "https://petstore.swagger.io/v2";

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

        /*RequestBody body = new FormBody.Builder()
                .add("id", "5")
                .add("username", "test")
                .add("firstName", "test2")
                .add("lastName", "test4")
                .add("email", "test@adsfasdf.com")
                .add("password", "patapum")
                .add("phone", "654321987")
                .add("userStatus", "0")
                .build();*/

        RequestBody body = RequestBody.create(JSON, bodyNewUser.toString());

        Request createUser = new Request.Builder()
                .url(baseUrl + "/user/user377")
                .build();

        try (Response response = client.newCall(createUser).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get respone body
            System.out.println(response.body().string());
        }

    }
}
