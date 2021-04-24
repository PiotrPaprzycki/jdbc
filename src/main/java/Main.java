import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        while (true) {
            System.out.println("1. Display all wydatki");
            System.out.println("2. Display all przychody");
            System.out.println("3. Add transaction");
            System.out.println("4. Update transaction");
            System.out.println("5. Delete transaction");
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
                case  "4":
                    updateTransaction();
                    break;
                case "5":
                    deleteTransaction();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Bad option!");
            }
        }
    }

    private static void deleteTransaction() {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.deleteTransaction();
    }

    private static void updateTransaction() {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.updateTransaction();
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
        transactionDao.addTransaction();
    }
}