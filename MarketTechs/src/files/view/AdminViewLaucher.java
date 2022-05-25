package files.view;

import java.util.Scanner;

public class AdminViewLaucher {
    public static Scanner scanner = new Scanner(System.in);


    public static void run() {
        AdminView adminView = new AdminView();
        boolean is = false;
        do {
            try {
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
                        adminView.showAdmin();
                        break;
                    case 4:
                        Menu.MainMenu();
                        break;
                    case 5:
                        Menu.exit();
                        System.exit(0);
                    default:
                        System.out.println("Incorrect! Please Try Again!");
                        break;
                }
            }catch (Exception E){
                System.out.println("Incorrect! Please Try Again!");
            }
        } while (!is);

    }
}
