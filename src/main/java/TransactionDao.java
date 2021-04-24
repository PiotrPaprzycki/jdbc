import java.sql.*;
import java.time.LocalDate;

public class TransactionDao {
    private final Connection connection;

    public TransactionDao() {
        try {
            String url = "jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC";
            this.connection = DriverManager.getConnection(url, "root", "pepe-23");
        } catch (SQLException e) {
            System.out.println("Error when trying to connect to database ... " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateTransaction(String decription, String newDecription) {
        final String sql = String.format("UPDATE transaction SET description = ? WHERE description = ?");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(2, decription);
            statement.setString(1, newDecription);
            int row = statement.executeUpdate();
            System.out.println("Zaktualizowano rekord: " + row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteTransaction(String description) {
        final String sql = String.format("DELETE FROM transaction WHERE description = ?");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, description);
            int row = statement.executeUpdate();
            System.out.println("Usunięto rekord: " + row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void addTransaction(String type, String decription, int amount) {
        final String sql = String.format("INSERT INTO transaction (type,description,amount,data) VALUES ('%s', '%s', '%s', '%s')",
                type, decription, amount, String.valueOf(LocalDate.now()));
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int row = statement.executeUpdate(sql);
            System.out.println("Dodano rekord: " + row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void selectWydatek() {
        String sql = "SELECT * FROM transaction WHERE type like 'WYDATEK'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet allTransactions = preparedStatement.executeQuery();

            while (allTransactions.next()) {
                String type = allTransactions.getString("type");
                String description = allTransactions.getString("description");
                Integer amount = allTransactions.getInt("amount");
                Date date = allTransactions.getDate("data");
                System.out.println(type + " " + description + " " + amount + " " + date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void selectPrzychod() {
        String sql = "SELECT * FROM transaction WHERE type like 'PRZYCHOD'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet allTransactions = preparedStatement.executeQuery();

            while (allTransactions.next()) {
                String type = allTransactions.getString("type");
                String description = allTransactions.getString("description");
                Integer amount = allTransactions.getInt("amount");
                Date date = allTransactions.getDate("data");
                System.out.println(type + " " + description + " " + amount + " " + date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}