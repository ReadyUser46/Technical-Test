import Task1.Task1;
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
