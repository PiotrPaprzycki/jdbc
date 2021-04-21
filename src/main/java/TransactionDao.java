import java.sql.*;

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

    void save(Transaction transaction) {
        // Prepare statement w lekcji
        final String sql = String.format("INSERT INTO transaction (type,description,amount,data) VALUES ('%s', '%s', '%s', '%s')",
                transaction.getType(), transaction.getDescription(), transaction.getAmount(), Date.valueOf(transaction.getLocalDate()));
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                transaction.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void selectAll() {
        final String sql = String.format("SELECT * FROM transaction");
        try (Statement statement = connection.createStatement()) {
            ResultSet allTransactions = statement.executeQuery(sql);
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