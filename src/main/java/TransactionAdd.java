import java.time.LocalDate;
import java.util.Scanner;

public class TransactionAdd {

    public static Transaction inputTransactionInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj opis transakcji: ");
        String description = scanner.nextLine();

        System.out.println("Podaj kwote transakcji: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj date transkacji w formacie RRRR-MM-DD");
        String transactionDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(transactionDate);

        while (true) {
            System.out.println("Wybierz typ transakcji:");
            System.out.println("1. Przychód");
            System.out.println("2. Wydatek");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    return new Transaction(TransactionType.INCOME, description, amount, date);
                case "2":
                    return new Transaction(TransactionType.EXPENSE, description, -amount, date);
                default:
                    System.out.println("Wybrano nieprawidłowy rodzaj transakcji");
            }
        }
    }
}
