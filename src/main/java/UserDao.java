import java.sql.*;
import java.util.Optional;

public class UserDao {
    private final Connection connection;

    public UserDao() {
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

    void save(User user) {
        final String sql = String.format("INSERT INTO user (imie, nazwisko) VALUES ('%s', '%s')",
                user.getImie(), user.getNazwisko());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}