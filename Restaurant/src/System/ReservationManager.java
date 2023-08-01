package System;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationManager {
    public void makeReservation(int tableNumber, String guestName, String reservationTime) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO reservations (table_number, reservation_time, guest_name) VALUES (?, ?, ?)")) {

            preparedStatement.setInt(1, tableNumber);
            preparedStatement.setString(2, reservationTime);
            preparedStatement.setString(3, guestName);
            preparedStatement.executeUpdate();
            System.out.println("Reservation made successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayReservations() {
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM reservations")) {

            System.out.println("======= Reservations =======");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tableNumber = resultSet.getInt("table_number");
                String reservationTime = resultSet.getString("reservation_time");
                String guestName = resultSet.getString("guest_name");
                System.out.println(id + ". Table " + tableNumber + " - " + guestName + " at " + reservationTime);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}