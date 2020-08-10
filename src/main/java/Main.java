import Task2.Task2;

import java.util.InputMismatchException;
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
                    // actions
                    System.out.println("task 1");
                    break;
                case 2:
                    String queryString = "automatización";
                    String screenShootPath = "C:\\Users\\sca_m\\Pictures\\seleniumPictures\\";

                    System.out.println("----------- TASK 2 ------------");
                    Task2 task2 = new Task2(queryString, screenShootPath);

                    task2.onSearchQuery();
                    task2.onGetWikiResult();
                    task2.onTakeFullScreenShoot();
                    task2.onTakeViewAreaScreenShoot();
                    task2.onCheckFirstAutomationProcess();

                    break;
                case 3:
                    // actions
                    System.out.println("task 3");
                    break;
                default:
                    // actions
                    System.out.println("You must introduce a valid input (1/2/3)");

            }
        } catch (InputMismatchException e) {
            System.out.println("You must introduce a valid input (1/2/3)");
        }
    }

}
