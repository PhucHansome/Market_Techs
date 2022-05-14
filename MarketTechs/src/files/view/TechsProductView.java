package files.view;

import java.util.Scanner;

public class TechsProductView {
    public static Scanner scanner = new Scanner(System.in);

    public static void run() {
        boolean is = false;
        do {
            Menu.managementMenu();
            System.out.println("\n Select Function");
            System.out.print("☛ ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Thêm sản phẩm");
                    break;
                case 2:
                    TechsProductView.run();
                    break;
                case 3:
                    TechsProductView.run();
                    break;
                case 4:
                    TechsProductView.run();
                    break;
                case 5:
                    Menu.lauchMainMenu();
                    break;
                case 6:
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    TechsProductView.run();
                    is = true;
            }
        } while (!is);
    }
}
