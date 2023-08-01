package System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuItemManager {
    public void addMenuItem(String name, double price) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO menu_items (name, price) VALUES (?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.executeUpdate();
            System.out.println("Menu item added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayMenu() {
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM menu_items")) {

            System.out.println("======= Menu =======");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                System.out.println(id + ". " + name + " - $" + price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}