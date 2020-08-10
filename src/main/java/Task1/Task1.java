package Task1;

import java.awt.*;
import java.io.File;

public class Task1 {

    public static void onOpeningResourceDirectory() {

        String task1ResourcesDir;
        if (System.getProperty("os.name").contains("Windows")) {
            task1ResourcesDir = System.getProperty("user.dir") + "\\src\\main\\resources\\task1";
        } else {
            task1ResourcesDir = System.getProperty("user.dir") + "/src/main/resources/task1";
        }

        System.out.println("Opening Task 1 Resource directory... \n" + task1ResourcesDir);
        System.out.println("\nThis directory has 3 folders. Each one contains a complete report for each bug");

        try {
            Desktop.getDesktop().open(new File(task1ResourcesDir));

        } catch (Exception e) {
            System.out.printf("The directory located at %s couldn't be opened\n", task1ResourcesDir);
            e.printStackTrace();
        }

    }

}

