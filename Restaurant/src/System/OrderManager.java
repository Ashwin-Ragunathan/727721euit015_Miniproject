package System;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderManager {
    public void placeOrder(int reservationId, int menuItemId, int quantity) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO orders (reservation_id, menu_item_id, quantity) VALUES (?, ?, ?)")) {

            preparedStatement.setInt(1, reservationId);
            preparedStatement.setInt(2, menuItemId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
            System.out.println("Order placed successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayOrders(int reservationId) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT menu_items.name, menu_items.price, orders.quantity FROM orders " +
                             "JOIN menu_items ON orders.menu_item_id = menu_items.id " +
                             "WHERE orders.reservation_id = ?")) {

            preparedStatement.setInt(1, reservationId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("======= Orders for Reservation ID " + reservationId + " =======");
            while (resultSet.next()) {
                String itemName = resultSet.getString("name");
                double itemPrice = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                System.out.println(itemName + " - $" + itemPrice + " x " + quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}