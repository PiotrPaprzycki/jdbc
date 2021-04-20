import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC", "root", "pepe-23");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM pracownik ORDER BY nazwisko");

        while (resultSet.next()) {
            String imie = resultSet.getString(4);
            String nazwisko = resultSet.getString(5);
            System.out.println("Imię: " + imie + " " + "Nazwisko: " + nazwisko);
        }

    }
}
