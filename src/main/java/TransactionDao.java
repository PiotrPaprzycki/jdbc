import java.sql.*;

public class TransactionDao {
    private final Connection connection;

    public TransactionDao() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC", "root", "pepe-23");
        } catch (SQLException e) {
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

}