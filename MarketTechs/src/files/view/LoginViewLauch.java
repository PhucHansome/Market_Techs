package files.view;

import java.util.Scanner;

public class LoginViewLauch {
    public static Scanner scanner = new Scanner(System.in);

    public static void run(){
        UserView userView = new UserView();
        do {
            Menu.firstLauch();
            System.out.println("\n Chọn chức năng!");
            System.out.print("☛ ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    Menu.login();
                    break;
                case 2:
                    System.out.println("đăng ký user");
                    break;
                default:
                    System.out.println("chọn chức năng không đúng");
                    break;
            }

        }while (true);
    }
}
