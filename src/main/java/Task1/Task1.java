package Task1;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Task1 {

    private static final String task1ResourcesDir = System.getProperty("user.dir") + "\\src\\main\\resources\\task1";

    public static void onOpeningResourceDirectory() {

        System.out.println("Opening Task 1 Resource directory... \n" + task1ResourcesDir);
        System.out.println("\nThis directory has 3 folders. Each one contains a complete report for each bug");

        try {
            Desktop.getDesktop().open(new File(task1ResourcesDir));
        } catch (IOException e) {
            System.out.printf("The directory located at %s couldn't be opened", task1ResourcesDir);
        }

    }


}
