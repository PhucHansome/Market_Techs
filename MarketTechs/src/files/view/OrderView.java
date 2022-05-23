package files.view;

import files.model.Order;
import files.model.OrderItem;
import files.model.Techs;
import files.service.*;
import files.utils.AppUtils;
import files.utils.InstantUtils;
import files.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final Scanner scanner = new Scanner(System.in);
    private final ITechsSevice techsSevice;
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;

    public OrderView() {
        techsSevice = TechsSevice.getInstance();
        orderService = OrderService.getInstance();
        orderItemService = OrderItemService.getInstance();
    }

    public OrderItem addOrderItem(long orderId) {
        do {
            try {
                orderItemService.findAll();
                TechsProduct techsProductView = new TechsProduct();
                techsProductView.showTechs1();
                long id = System.currentTimeMillis() / 1000;
                System.out.println("Press Product Id: ");
                System.out.print("⭆ ");
                int techsId = Integer.parseInt(scanner.nextLine());
                while (!techsSevice.existById(techsId)) {
                    System.out.println("Product ID Does Not Exist ");
                    System.out.println("Press Product Id:: ");
                    System.out.print("⭆ ");
                    techsId = Integer.parseInt(scanner.nextLine());
                }
                Techs product = techsSevice.getTechsById(techsId);
                double price = product.getPriceTechs();
                System.out.println("Press Quantity");
                System.out.print("⭆ ");
                int quantity = Integer.parseInt(scanner.nextLine());
                while (!checkQualityTechs(product, quantity)) {
                    System.out.println("Exceeded Quantity! Please Try again");
                    System.out.println("Press Quantity: ");
                    System.out.print("⭆ ");
                    quantity = Integer.parseInt(scanner.nextLine());
                }
                String Techname = product.getNameTechs();
                double total = quantity * price;
                OrderItem orderItem = new OrderItem(id, price, quantity, orderId, techsId, Techname, total);
                techsSevice.updateQuantityy(techsId, quantity);
                return orderItem;
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!!");
            }
        } while (true);
    }

    public boolean checkQualityTechs(Techs techs, int quantity) {
        if (quantity <= techs.getQuantityTechs())
            return true;
        else
            return false;
    }

    public void addOrder() {
        try {
            orderItemService.findAll();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Press Full Name(Example: Phuc Nguyen) ");
            System.out.print(" ⭆ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Name " + name + " Incorrect!Please Try Again (Capitalize First Letter And Unsigned )");
                System.out.println("Enter Name(Example Phuc Nguyen) ");
                System.out.print(" ⭆ ");
                name = scanner.nextLine();
            }
            System.out.println("Press Number Phone");
            System.out.print(" ⭆ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Number Phone " + phone + "Incorrect! Please Try Again(Start With 0 And Total 10 Numbers)");
                System.out.println("Press Number Phone (Example: 0349108527)");
                System.out.print(" ⭆ ");
                phone = scanner.nextLine();
            }
            System.out.println("Press Address:");
            System.out.print(" ⭆ ");
            String address = scanner.nextLine();
            while (address.isEmpty()) {
                System.out.println("Can't Be Left Empty");
                System.out.println("Press Address:");
                System.out.print(" ⭆ ");
                address = scanner.nextLine();
            }
            Order order = new Order(orderId, name, phone, address);
            orderService.add(order);
            List<OrderItem> orderItems = addOrderItems(orderId);

            for (OrderItem orderItem : orderItems) {
                orderItemService.add(orderItem);
            }

            System.out.println("Order Creation Successful");
            System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
            System.out.println("㋡                                         ㋡");
            System.out.println("㋡           1. Create Next Order          ㋡");
            System.out.println("㋡           2. Print Bill                 ㋡");
            System.out.println("㋡           3. Turn Back                  ㋡");
            System.out.println("㋡           4. Exit                       ㋡");
            System.out.println("㋡                                         ㋡");
            System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
            System.out.print(" ⭆ ");
            do {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addOrderItems(orderId);
                        break;
                    case 2:
                        showPaymentInfo(order);
                        break;
                    case 3:
                        OrderViewLauncher.run();
                        break;
                    case 4:
                        AppUtils.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect! Please Try Again!!");
                        break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Incorrect! Please Try Again!!");
        }
    }

    public void showPaymentInfo(Order order) {

        try {
            System.out.println("=============================================================================");
            System.out.println("|                                   BILL                                    |");
            System.out.println("=============================================================================");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "Full Name   :", order.getFullName(), "");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "NumberPhone :", order.getMobile(), "");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "Address     :", order.getAddress(), "");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "Creat Date  : ", InstantUtils.instantToString(order.getCreatedAt()), "");
            System.out.println("=============================================================================");
            System.out.printf(" %-20s\t %-25s %-25s \n", "Product Name", "Quantity", "Price");
            List<OrderItem> orderItem = orderItemService.findAll();
            double sum = 0;
            for (OrderItem orderItem1 : orderItem) {
                if (orderItem1.getOrderId() == order.getId()) {
                    sum += orderItem1.getTotal();
                    orderItem1.setGrandTotal(sum);
                    orderItemService.update(orderItem1.getOrderId(), orderItem1.getPrice(),sum);
                    System.out.printf(" %-20s\t %-25s %-25s \n",
                            orderItem1.getProductName(),
                            orderItem1.getQuantity(),
                            AppUtils.doubleToVND(orderItem1.getPrice()));
                }
            }
            System.out.println("                                             Total:" + AppUtils.doubleToVND(sum) + "\n");
            System.out.println("-------------------------------Technology Market-----------------------------\n");
            System.out.println("                                                     Sign\n\n");
            System.out.println("                                             ๖ۣۜP๖ۣۜH๖ۣۜU๖ۣۜC ๖ۣۜN๖ۣۜG๖ۣۜU๖ۣۜY๖ۣۜE๖ۣۜN");
            boolean is = true;
            do {
                System.out.println("Enter 'q' Turn Back \t|\t 't' Exit");
                System.out.print("⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Incorrect!Please Try Again!!");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Incorrect! Please Try Again!!");
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderItem> orderItems = orderItemService.findAll();
        OrderItem newOrderItem = new OrderItem();
        try {
            double sum = 0;
            System.out.println("----------------------------------------------------------------------LIST ORDER--------------------------------------------------------------------------------");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
//                        break;
                    }
                }
//                double result = newOrderItem.getQuantity() * newOrderItem.getPrice();
                sum += newOrderItem.getTotal();
                newOrderItem.setGrandTotal(sum);
                orderItemService.update(newOrderItem.getOrderId(),newOrderItem.getPrice(), sum);
                System.out.println("================================================================================================================================================================");
                System.out.printf("|\t%-20s%-20s%-30s%-20s%-25s%41s|\n", "Id: ", order.getId(), " ", "Customer Name", order.getFullName(), "");
                System.out.printf("|\t%-20s%-20s%-30s%-20s%-25s%41s|\n", "Number Phone: ", order.getMobile(), " ", "Address: ", order.getAddress(), "");
                System.out.printf("|\t%-20s%-20s%-30s%-20s%-20d%-20s%-10s%-15s\t|\n", "Product Name", newOrderItem.getProductName(), " ", "Quantity", newOrderItem.getQuantity(),
                        " ", "Prince", AppUtils.doubleToVND(newOrderItem.getPrice()));
                System.out.println("======================================================================================================================================== total: " + AppUtils.doubleToVND(newOrderItem.getTotal()) + "\n");

            }
            System.out.println("|-------------------Total Revenue:" + AppUtils.doubleToVND(newOrderItem.getGrandTotal()) + "-------------------|");
            System.out.println("===================================================================");
            boolean is = true;
            do {
                System.out.println("Press 'q' Turn Back \t|\t 't' Exit");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Incorrect! Please Try Again!!");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Incorrect! Please Try Again!!");
        }
    }

    public List<OrderItem> addOrderItems(long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        System.out.println("Enter The Product Number You Want To Order: ");
        Integer choice = Integer.parseInt(scanner.nextLine());
        int count = 0;
        do {
            orderItems.add(addOrderItem(orderId));
            count++;
        } while (count < choice);
        return orderItems;
    }
}
