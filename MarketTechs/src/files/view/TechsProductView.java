package files.view;

import java.util.Scanner;

public class TechsProductView {
    public static Scanner scanner = new Scanner(System.in);

    public static void run() {
        TechsProduct techsProduct = new TechsProduct();
        boolean is = false;
        do {
            try {
                Menu.managementMenu();
                System.out.println("\n Select Function");
                System.out.print("☛ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        techsProduct.addTechs();
                        break;
                    case 2:
                        techsProduct.updateProductTechs();
                        break;
                    case 3:
                        techsProduct.remove();
                        break;
                    case 4:
                        techsProduct.showTechs();
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
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again");
                e.printStackTrace();
            }
        } while (!is);
    }
}
