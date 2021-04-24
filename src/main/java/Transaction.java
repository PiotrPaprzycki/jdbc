import java.time.LocalDate;

public class Transaction {
    private Integer id;
    private Type type;
    private String description;
    private double amount;
    private LocalDate localDate;

    public Transaction(Type type, String description, double amount, LocalDate localDate) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.localDate = localDate;
    }

    public Transaction(Integer id, Type type, String description, double amount, LocalDate localDate) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.localDate = localDate;
    }
}