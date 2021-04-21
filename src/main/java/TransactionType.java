public enum TransactionType {
    INCOME("przychód"), EXPENSE("wydatek");

    private String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
