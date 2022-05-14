package files.view;

import java.util.Scanner;

public class AdminViewLaucher {
    public static Scanner scanner = new Scanner(System.in);


    public static void run() {
        AdminView adminView = new AdminView();
        boolean is = false;
        do {
            Menu.userMenu();
            System.out.println("\n Select Function");
            System.out.print("☛ ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    adminView.addAdmin();
                    break;
                case 2:
                    adminView.updateAdmin();
                    break;
                case 3:
                    adminView.remove();
                    break;
                case 4:
                    adminView.showAdmin();
                    break;
                case 5:
                    Menu.lauchMainMenu();
                    break;
                case 6:
                    Menu.exit();
                    System.exit(0);
                default:
                    System.out.println("Chọn chức năng không đúng! vui lòng chọn lại");
                    break;
            }
        } while (!is);

    }
}
