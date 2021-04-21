import java.time.LocalDate;

public class Transaction {
    private Integer id;
    private TransactionType type;
    private String description;
    private double amount;
    private LocalDate date;

    public Transaction(TransactionType type, String description, double amount, LocalDate date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(Integer id, Transaction transaction) {
        this.id = id;
        this.type = transaction.getType();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
    }

    public Transaction(Integer id, TransactionType type, String description, double amount, LocalDate date) {
        this(type, description, amount, date);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transakcja: " + type.getDescription() + ", Opis: " + description + ", kwota: " + amount + ", data: " + date;
    }
}
