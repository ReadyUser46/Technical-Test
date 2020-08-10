import Task1.Task1;
import Task2.Task2;
import Task3.Task3;
import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("1 - Task 1: Exploratory test and bugs report");
        System.out.println("2 - Task 2: Webpage automation");
        System.out.println("3 - Task 3: API");
        System.out.println("\nSelect the task you want to execute (1/2/3): ");

        Scanner input = new Scanner(System.in);

        try {
            int response = input.nextInt();
            switch (response) {

                case 1:
                    System.out.println("----------- TASK 1 ------------");
                    Task1.onOpeningResourceDirectory();
                    break;

                case 2:
                    String queryString = "automatización";

                    System.out.println("----------- TASK 2 ------------");
                    Task2 task2 = new Task2(queryString);

                    task2.onSearchQuery();
                    task2.onGetWikiResult();
                    task2.onTakeFullScreenShoot();
                    task2.onTakeViewAreaScreenShoot();
                    task2.onCheckFirstAutomationProcess();
                    break;

                case 3:
                    System.out.println("----------- TASK 3 ------------");

                    // Fill in the details for a new user
                    JSONObject newUserJson = new JSONObject();
                    newUserJson.put("id", 5);
                    newUserJson.put("username", "user377");
                    newUserJson.put("firstName", "Joker377");
                    newUserJson.put("lastName", "Joker378");
                    newUserJson.put("email", "joker@joker.com");
                    newUserJson.put("password", "user377");
                    newUserJson.put("phone", "377");
                    newUserJson.put("userStatus", 0);

                    Task3 task3 = new Task3();

                    task3.onPostUser(newUserJson);
                    System.out.println("---------------------------------");
                    task3.onGetUser(newUserJson.getString("username"));
                    System.out.println("---------------------------------");
                    Map<Long,String> tupleTask3 = task3.onGetPetFindByStatus("sold");
                    System.out.println("---------------------------------");
                    task3.onGetSamePetNames(tupleTask3);
                    System.out.println("---------------------------------");
                    break;

                default:
                    System.out.println("You must introduce a valid input (1/2/3)");
            }
        } catch (InputMismatchException e) {
            System.out.println("You must introduce a valid input (1/2/3)");
        }
    }

}
