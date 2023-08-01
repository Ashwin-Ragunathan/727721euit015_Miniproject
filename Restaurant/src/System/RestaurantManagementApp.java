package System;
import java.util.Scanner;

public class RestaurantManagementApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MenuItemManager menuItemManager = new MenuItemManager();
            ReservationManager reservationManager = new ReservationManager();
            OrderManager orderManager = new OrderManager();

            int choice;
            do {
                System.out.println("======== Restaurant Management ========");
                System.out.println("1. Add Menu Item");
                System.out.println("2. Display Menu");
                System.out.println("3. Make Reservation");
                System.out.println("4. Display Reservations");
                System.out.println("5. Place Order");
                System.out.println("6. Display Orders");
                System.out.println("0. Exit");
                System.out.println("Enter your choice:");

                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Enter menu item name:");
                        String itemName = scanner.nextLine();
                        System.out.println("Enter menu item price:");
                        double itemPrice = scanner.nextDouble();
                        menuItemManager.addMenuItem(itemName, itemPrice);
                        break;
                    case 2:
                        menuItemManager.displayMenu();
                        break;
                    case 3:
                        System.out.println("Enter table number:");
                        int tableNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter guest name:");
                        String guestName = scanner.nextLine();
                        System.out.println("Enter reservation time (YYYY-MM-DD HH:MM:SS):");
                        String reservationTime = scanner.nextLine();
                        reservationManager.makeReservation(tableNumber, guestName, reservationTime);
                        break;
                    case 4:
                        reservationManager.displayReservations();
                        break;
                    case 5:
                        System.out.println("Enter reservation ID:");
                        int reservationId = scanner.nextInt();
                        System.out.println("Enter menu item ID:");
                        int menuItemId = scanner.nextInt();
                        System.out.println("Enter quantity:");
                        int quantity = scanner.nextInt();
                        orderManager.placeOrder(reservationId, menuItemId, quantity);
                        break;
                    case 6:
                        System.out.println("Enter reservation ID:");
                        int reservationIdForOrder = scanner.nextInt();
                        orderManager.displayOrders(reservationIdForOrder);
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}