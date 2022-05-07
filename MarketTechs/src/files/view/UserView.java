package files.view;

import files.object.Role;
import files.object.User;
import files.service.IUserService;
import files.service.UserService;
import files.utils.ValidateUtils;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = new UserService();
    }

    private boolean isRetry(InputOption inputOption) {
        do {
            try {
                switch (inputOption) {
                    case ADD:
                        System.out.println("Nhấn 'y'để tiếp thêm người dùng \t|\t 'q' để quay lại \t|\t 't' để thoát");
                        break;
                    case UPDATE:
                        System.out.println("Nhấn 'y' để sửa tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
                        break;
                    case SHOW:
                        System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + inputOption);

                }
                System.out.print("➲ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "q":
                        return false;
                    case "t":
                        Menu.exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! vui lòng chọn lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
                ex.printStackTrace();
            }
        } while (true);
    }

    private Integer inputId(InputOption option) {
        Integer id = null;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id");
                System.out.print("➲ ");
                while (userService.existById(id = Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Id này đã tồn tại. Vui lòng nhập lại id khác");
                    System.out.print("➲ ");
                }
                break;
            case UPDATE:
                System.out.println("Nhập Id mà bạn muốn sửa:");
                System.out.print("➲ ");
                while (!userService.existById(id = Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Id này đã tồn tại. Vui lòng nhập id khác!");
                    System.out.print("➲ ");
                }
                break;
        }
        return id;
    }

    public String inputUsername() {
        System.out.println("Nhập user name: ");
        System.out.print("➲ ");
        String username;
        while (userService.checkDuplicateUserName(username = scanner.nextLine())) {
            System.out.println("Username này đã tồn tại. Vui lòng nhập lại!");
            System.out.print("➲ ");
        }
        return username;
    }

    private String inputFullname(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập họ và tên(vd: Nguyen Van Phuc) ");
                break;
            case UPDATE:
                System.out.println("Nhập tên mà bạn muốn sửa đổi");
                break;
        }
        System.out.print("➲ ");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = scanner.nextLine())) {
            System.out.println("Tên " + fullName + "không đúng định dạng. Vui lòng nhập lại (Phải viết hoa chữ cái đầu và không dấu)");
            System.out.println("Nhập tên(vd: Phuc Nguyen )");
            System.out.print("➲ ");
        }
        return fullName;
    }

    public String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số điện thoại(vd: 0345567489): ");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại mà bạn muốn đổi");
                break;
        }
        System.out.print("➲ ");
        String phone;
        while (!ValidateUtils.isPhoneValid(phone = scanner.nextLine())) {
            System.out.println("Số " + phone + " Của bạn không đúng. Vui lòng nhập lại! (Số điện thoại bắt đầu là 10 số và bắt đầu là số 0");
            System.out.println("Nhập số điện thoại (Vd: 0304456748)");
            System.out.print("➲ ");
            if (userService.checkDuplicatePhone(phone)) {
                System.out.println("Số này đã tồn tại");
                System.out.print("➲ ");
            }
        }
        return phone;
    }

    private String inputEmail() {
        System.out.println("Nhập email(vd: phucnguyen@gmail.com) ");
        System.out.print("➲ ");
        String email;
        while (!ValidateUtils.isEmailValid(email = scanner.nextLine())) {
            System.out.println("Email " + email + " của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại");
            System.out.println("Nhập email(vd: phucnguyen@gmail.com");
            System.out.print("➲ ");
        }
        return email;
    }

    private String inputPassword() {
        System.out.println("Nhập mật khẩu(Mật khẩu phải > 8 ký tự)");
        System.out.print("➲ ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())) {
            System.out.println("Mật khẩu yếu! Vui lòng nhập lại");
            System.out.print("➲ ");
        }
        return password;
    }

    private String inputAddress(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập địa chỉ(VD:Hue)");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ mà bạn muốn đổi");
                break;

        }
        System.out.print("➲ ");
        String address = scanner.nextLine();
        return address;
    }

    public void addUser() {
        do {
            try {
                Integer id = inputId(InputOption.ADD);
                String username = inputUsername();
                String password = inputPassword();
                String fullName = inputFullname(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();

                User user = new User(id, username, password, fullName, phone, email, address, Role.USER);
                setRole(user);
                userService.add(user);
            } catch (Exception e) {
                System.out.println("Nhập sai. Vui lòng nhập lại");
            }
        } while (isRetry(InputOption.ADD));
    }

    public void setRole(User user) {
        System.out.println("= = SET ROLE = =");
        System.out.println("∥              ∥");
        System.out.println("∥   1. USER    ∥");
        System.out.println("∥   2. ADMIN   ∥");
        System.out.println("∥              ∥");
        System.out.println("= = = =  = = = = ");
        System.out.println("Chọn Role: ");
        System.out.print("➲ ");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("nhập không đúng! vui lòng nhập lại");
                setRole(user);
        }
    }

    public void showUser() {
        System.out.println("------------------------------------------------LIST CUSTOMER------------------------------------------------");
        System.out.printf("%-5s %-22s %-15s %-22s %-20s %-20s \n", "ID", "Tên", "Số Điện Thoại", "Email", "Địa Chỉ", "Quyền Truy Cập");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf("%-5d %-22s %-15s %-22s %-20s %-25s  \n", user.getId(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress(), user.getRole());

        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        isRetry(InputOption.SHOW);
    }

    public void updateUser() {
        boolean isRetry = false;
        do {
            try {
                System.out.println("------------------------------------------------LIST CUSTOMER------------------------------------------------");
                System.out.printf("%-5s %-22s %-15s %-22s %-20s %-20s \n", "ID", "Tên", "Số Điện Thoại", "Email", "Địa Chỉ", "Quyền Truy Cập");
                List<User> users = userService.getUsers();
                for (User user : users) {
                    System.out.printf("%-5d %-22s %-15s %-22s %-20s %-25s  \n", user.getId(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress(), user.getRole());

                }
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                Integer id = inputId(InputOption.UPDATE);
                System.out.println("\t┌ - - - - - - - EDIT - - - - - - - ┐");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t︲        1. Đổi tên               ︲");
                System.out.println("\t︲        2. Sửa số điện thoại     ︲");
                System.out.println("\t︲        3. Sửa địa chỉ           ︲");
                System.out.println("\t︲        4. Quay lại              ︲");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t└ - - - - - - - - - - - - - - - -  ┘");
                int option;
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print("➲ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 4 || option < 1) {
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        continue;
                    }
                    break;
                } while (true);
                User newUser = new User();
                newUser.setId(id);
                switch (option) {
                    case 1:
                        String name = inputFullname(InputOption.UPDATE);
                        newUser.setName(name);
                        userService.updateName(newUser);
                        System.out.println("bạn đã đổi thành công!");
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newUser.setPhone(phone);
                        userService.updatePhone(newUser);
                        System.out.println("Bạn đã đổi số điện thoại thành công!!");
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(address);
                        userService.updateAdress(newUser);
                        System.out.println("Bạn đã đổi thành công !!");
                        break;
                }
                isRetry = option != 4 && isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (isRetry);
    }

    public void loginAdmin() {
        System.out.println("* * * * * * * * * *LOGIN* * * * * * * * * *");
        System.out.println("UserName: ");
        System.out.print("➲ ");
        String username = scanner.nextLine();
        System.out.println("PassWord: ");
        System.out.print("➲ ");
        String password = scanner.nextLine();
        if (userService.loginAdmin(username, password) == null) {
            System.out.println("Bạn nhập tài khoản hoặc mật khẩu sai!làm ơn nhập lại ");
            option();
        } else {
            System.out.println("\t ĐĂNG NHẬP THÀNH CÔNG!!");
            System.out.println("\t CHÀO MỪNG CÁC BẠN ĐÃ ĐẾN VỚI MARKET TECHS \n");
        }
    }

    public void option() {
        System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ CHOICE ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("\t✦                                                                ✦");
        System.out.println("\t✦                   1. Nhấn 'y' để đăng nhâp lại                 ✦");
        System.out.println("\t✦                   2. Nhấn 'n' để thoát chương trình            ✦");
        System.out.println("\t✦                                                                ✦");
        System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.print("➲ ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "y":
                loginAdmin();
                break;
            case "n":
                Menu.exit();
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect! Please Try again!");
                option();

        }
    }
}