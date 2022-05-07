package files.view;

import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);


    public static void mainMenu() {
        System.out.println("\t❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ MAIN MENU ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂");
        System.out.println("\t❂                                             ❂");
        System.out.println("\t❂           1. Quản lý người dùng             ❂");
        System.out.println("\t❂           2. Quản lý sản phẩm               ❂");
        System.out.println("\t❂           3. Quản lý đơn đặt hàng           ❂");
        System.out.println("\t❂           4. Thoát khỏi chương trình        ❂");
        System.out.println("\t❂                                             ❂");
        System.out.println("\t❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂");
    }

    public static void login(){
        UserView userView = new UserView();
        userView.loginAdmin();
        lauchMainMenu();
    }
    public static void  lauchMainMenu() {
        boolean is = false;
        do {
            mainMenu();
            System.out.println("\nChọn chức năng");
            System.out.print("☛ ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    UserViewLaucher.run();
                    break;
                case 2:
                    TechsProductView.run();
                    break;
                case 3:
                    OrderView.run();
                    break;
                case 4:
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    Menu.lauchMainMenu();
                    is = true;
                    break;
            }
        } while (!is);
    }

    public static void userMenu() {
        System.out.println("\t卍 卍 卍 卍 卍 卍 卍 卍ADMIN MENU卍 卍 卍 卍 卍 卍 卍 卍");
        System.out.println("\t卍                                              卍");
        System.out.println("\t卍           1. Thêm tài khoản mới              卍");
        System.out.println("\t卍           2. Chỉnh sửa tài khoản             卍");
        System.out.println("\t卍           3. Hiển thị danh sách tài khoản    卍");
        System.out.println("\t卍           4. Quay lại Main Menu              卍");
        System.out.println("\t卍           5. Thoát khỏi chương trình         卍");
        System.out.println("\t卍                                              卍");
        System.out.println("\t卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍");
    }

    public static void managementMenu() {
        System.out.println("\t♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉MANAGENMENT MENU♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉");
        System.out.println("\t♉                                                   ♉");
        System.out.println("\t♉           1. Thêm sản phẩm                        ♉");
        System.out.println("\t♉           2. Sửa thông tin sản phẩm               ♉");
        System.out.println("\t♉           3. Xóa sản phẩm                         ♉");
        System.out.println("\t♉           4. Hiển thị toàn bộ sản phẩm            ♉");
        System.out.println("\t♉           5. Quay lại Main Menu                   ♉");
        System.out.println("\t♉           6. Thoát khỏi chương trình              ♉");
        System.out.println("\t♉                                                   ♉");
        System.out.println("\t♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉");
    }

    public static void orderMenu() {
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ORDER MENU♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧               1. Tạo order                         ♧");
        System.out.println("\t♧               2. Xem danh sách order               ♧");
        System.out.println("\t♧               3. Quay lại Main Menu                ♧");
        System.out.println("\t♧               4. Thoát khỏi chương trình           ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧  ♧");
    }
    public static void exit(){
        System.out.println("\tTạm biệt! Hẹn gặp lại");
        System.exit(5);
    }
}
