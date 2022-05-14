package files.view;

import files.object.Admin;
import files.object.Role;
import files.service.AdminService;
import files.service.IAdminService;
import files.utils.AppUtils;
import files.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private final IAdminService adminService;
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        adminService = new AdminService();
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
                while (adminService.existById(id = Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Id này đã tồn tại. Vui lòng nhập lại id khác");
                    System.out.print("➲ ");
                }
                break;
            case UPDATE:
                System.out.println("Nhập Id mà bạn muốn sửa:");
                System.out.print("➲ ");
                while (!adminService.existById(id = Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Id này đã tồn tại. Vui lòng nhập id khác!");
                    System.out.print("➲ ");
                }
                break;
        }
        return id;
    }

    public String inputAdminName() {
        System.out.println("Nhập user name: ");
        System.out.print("➲ ");
        String username;
        while (adminService.checkDuplicateUserName(username = scanner.nextLine())) {
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
            if (adminService.checkDuplicatePhone(phone)) {
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

    public void addAdmin() {
        do {
            try {
                Integer id = inputId(InputOption.ADD);
                String username = inputAdminName();
                String password = inputPassword();
                String fullName = inputFullname(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();

                Admin admin = new Admin(id, username, password, fullName, phone, email, address, Role.USER);
                setRole(admin);
                adminService.add(admin);
            } catch (Exception e) {
                System.out.println("Nhập sai. Vui lòng nhập lại");
            }
        } while (isRetry(InputOption.ADD));
    }

    public void setRole(Admin admin) {
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
                admin.setRole(Role.USER);
                break;
            case 2:
                admin.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("nhập không đúng! vui lòng nhập lại");
                break;
            //setRole(admin);
        }
    }

    public void showAdmin() {
        System.out.println("-----------------------------------------------------LIST CUSTOMER-----------------------------------------------------");
        System.out.printf("%-5s %-22s %-15s %-30s %-20s %-20s %-10s \n", "ID", "Name", "Number Phone", "Email", "Address", "ROLE","Date Creat");
        List<Admin> admins = adminService.getAdmin();
        for (Admin admin : admins) {
            System.out.printf("%-5d %-22s %-15s %-30s %-20s %-20s %-10s  \n", admin.getId(), admin.getName(), admin.getPhone(), admin.getEmail(), admin.getAddress(), admin.getRole(), admin.getCreatDate());

        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        isRetry(InputOption.SHOW);
    }

    public void updateAdmin() {
        boolean isRetry = false;
        do {
            try {
                showUser1();
                Integer id = inputId(InputOption.UPDATE);
                System.out.println("\t┌ - - - - - - - EDIT - - - - - - - ┐");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t︲        1. Edit Name             ︲");
                System.out.println("\t︲        2. Edit NumerPhone       ︲");
                System.out.println("\t︲        3. Edit Address          ︲");
                System.out.println("\t︲        4. Back                  ︲");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t└ - - - - - - - - - - - - - - - -  ┘");
                int option;
                do {
                    System.out.println("Select Function");
                    System.out.print("➲ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 4 || option < 1) {
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        continue;
                    }
                    break;
                } while (true);
                Admin newAdmin = new Admin();
                newAdmin.setId(id);
                switch (option) {
                    case 1:
                        String name = inputFullname(InputOption.UPDATE);
                        newAdmin.setName(name);
                        adminService.updateName(newAdmin);
                        System.out.println("bạn đã đổi thành công!");
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newAdmin.setPhone(phone);
                        adminService.updatePhone(newAdmin);
                        System.out.println("Bạn đã đổi số điện thoại thành công!!");
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newAdmin.setAddress(address);
                        adminService.updateAdress(newAdmin);
                        System.out.println("Bạn đã đổi thành công !!");
                        break;
                }
                isRetry = option != 4 && isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (isRetry);
    }


    public void adminLogin() {
        boolean isRetry;
        System.out.println("✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽ LOGIN MANAGER ✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽ ");
        do {
            System.out.println("Username");
            System.out.print(" ⭆ ");
            String username = scanner.nextLine();
            System.out.println("Mật khẩu");
            System.out.print(" ⭆ ");
            String password = scanner.nextLine();
            if (adminService.loginAdmin(username, password) == null) {
                System.out.println("Tài khoản không hợp lệ ");
                isRetry = isRetry();
            } else {
                System.out.println("Logged in successfully!! \n");
                System.out.println("Wellcome to the Market Techs\n");
                isRetry = false;
            }
        } while (isRetry);
    }

    private boolean isRetry() {
        do {
            try {
                System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ CHOICE ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.println("\t✦                                                                ✦");
                System.out.println("\t✦                   1. Press 'y' To Go Back                      ✦");
                System.out.println("\t✦                   2. Press 'n' to Exit                         ✦");
                System.out.println("\t✦                                                                ✦");
                System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.print("➲ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "n":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
                ex.printStackTrace();
            }
        } while (true);
    }


    public void showUser1() {
        System.out.println("------------------------------------------------LIST CUSTOMER------------------------------------------------");
        System.out.printf("%-5s %-22s %-15s %-30s %-20s %-10s %-10s \n", "ID", "Name", "Number Phone", "Email", "Address", "ROLE", "Date Creat");
        List<Admin> admins = adminService.getAdmin();
        for (Admin admin : admins) {
            System.out.printf("%-5d %-22s %-15s %-30s %-20s %-10s %-10s  \n", admin.getId(), admin.getName(), admin.getPhone(), admin.getEmail(), admin.getAddress(), admin.getRole(), admin.getCreatDate());

        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }
        public void remove() {
            showUser1();
            adminService.getAdmin();
            System.out.println("Press Id You Wanna Remove:");
            System.out.print("➲ ");
            int id = Integer.parseInt(scanner.nextLine());
            Admin admin = adminService.getUserById(id);
            System.out.println(admin);
            if (admin == null) {
                System.out.println("Không tìm thấy Id để xóa!!");
            } else {
                boolean check = true;
                System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄REMOVE COFIRM❄ ❄ ❄ ❄ ❄ ❄ ❄");
                System.out.println("\t❄                                          ❄");
                System.out.println("\t❄              1. Remove                   ❄");
                System.out.println("\t❄              2. Back                     ❄");
                System.out.println("\t❄                                          ❄");
                System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
                System.out.print("➲ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:

                        adminService.remove(admin);

                        System.out.println("Đã xóa User thành công!");
                        do {
                            System.out.println("\t------------------------------------------------------------");
                            System.out.println("\t| Press 'y' To Go Back\t|\t'n' To Exit  |");
                            System.out.println("\t------------------------------------------------------------");
                            System.out.print("➲ ");
                            String choic = scanner.nextLine();
                            switch (choic) {
                                case "y":
                                    AdminViewLaucher.run();
                                    break;
                                case "n":
                                    Menu.exit();
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Nhấn không đúng!! vui lòng chọn lại");
                                    check = false;
                            }
                        } while (!check);
                        break;
                    case 2:
                        AdminViewLaucher.run();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng!! vui lòng chọn lại ");
                }
            }
        }
    }