import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

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

        while (true) {
            System.out.println("1. Display all wydatki");
            System.out.println("1. Display all przychody");
            System.out.println("2. Add transaction");
            System.out.println("0. Quit");

            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            scanner.nextLine();

            switch (option) {
                case "1":
                    showAllWydatki();
                    break;
                    case "2":
                    showAllPrzychody();
                    break;
                case "3":
                    addNewTransactions();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Bad option!");
            }

        }


    }

    private static void showAllWydatki() {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.selectWydatek();
    }

    private static void showAllPrzychody() {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.selectPrzychod();
    }

    private static void addNewTransactions() {
        TransactionDao transactionDao = new TransactionDao();

        Transaction transaction = new Transaction(Type.WYDATEK, "zakupy", 111, LocalDate.now());
        Transaction transaction1 = new Transaction(Type.PRZYCHOD, "sprzedaz", 222, LocalDate.now());
        Transaction transaction2 = new Transaction(Type.WYDATEK, "zakupy", 333, LocalDate.now());

        transactionDao.save(transaction);
        transactionDao.save(transaction1);
        transactionDao.save(transaction2);
    }
}