package files.view;

import files.object.Techs;
import files.service.IAdminService;
import files.service.ITechsSevice;
import files.service.TechsSevice;

import java.util.Scanner;

public class TechsProduct {
    private final ITechsSevice techsSevice;
    private final Scanner scanner = new Scanner(System.in);

    public TechsProduct() {
        techsSevice = new TechsSevice();
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
    private Integer inputId(InputOption option){
        Integer id = null;
        boolean a = techsSevice.existById(id = Integer.parseInt(scanner.nextLine()));
        switch (option){
            case ADD:
                System.out.println("Press Id");
                System.out.print("➲ ");
                while (a){
                    System.out.println("This Id Already Exists!Please Try Again!!");
                    System.out.print("➲ ");
                }
                break;
            case UPDATE:
                System.out.println("Nhập Id mà bạn muốn sửa: ");
                System.out.print("➲ ");
                while (a){
                    System.out.println("This Id Already Exists!Please Try Again!!");
                    System.out.print("➲ ");
                }
                break;
        }
        return id;
    }
    private String inputTechsName(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Enter Product Name");
                break;
            case UPDATE:
                System.out.println("Enter The Product Name You Want To Modify");
                break;
        }
        System.out.print("➲ ");
        String nameTechs = scanner.nextLine();
        return nameTechs;
    }
}
