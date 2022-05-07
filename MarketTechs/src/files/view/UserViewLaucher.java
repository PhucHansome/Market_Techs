package files.view;

import java.util.Scanner;

public class UserViewLaucher {
    public static Scanner scanner = new Scanner(System.in);


    public static void run() {
        UserView userView = new UserView();
        boolean is = false;
        do {
            Menu.userMenu();
            System.out.println("\n chọn chức năng");
            System.out.print("☛ ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    userView.addUser();
                    break;
                case 2:
                    userView.updateUser();
                    break;
                case 3:
                    userView.showUser();
                    break;
                case 4:
                    Menu.lauchMainMenu();
                    break;
                case 5:
                    Menu.exit();
                    System.exit(0);
                default:
                    System.out.println("Chọn chức năng không đúng! vui lòng chọn lại");
                    break;
            }
        } while (!is);

    }
}
