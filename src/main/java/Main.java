import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {

//        try (
//                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC", "root", "pepe-23");
//                Statement statement = connection.createStatement();
//        ) {
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM pracownik ORDER BY nazwisko");
//
//            while (resultSet.next()) {
//                String imie = resultSet.getString("imie");
//                String nazwisko = resultSet.getString("nazwisko");
//                System.out.println("Imię: " + imie + " " + "Nazwisko: " + nazwisko);
//            }
//
//        }
        Transaction transaction = new Transaction(Type.WYDATEK, "zakupy", 111, LocalDate.now());
        Transaction transaction1 = new Transaction(Type.PRZYCHOD, "sprzedaz", 222, LocalDate.now());
        Transaction transaction2 = new Transaction(Type.WYDATEK, "zakupy", 333, LocalDate.now());

        TransactionDao transactionDao = new TransactionDao();

        transactionDao.save(transaction);
        transactionDao.save(transaction1);
        transactionDao.save(transaction2);
    }
}
