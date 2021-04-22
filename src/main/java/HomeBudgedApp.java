import java.util.Scanner;

public class HomeBudgedApp {
    private final TransactionDao dao = new TransactionDao();
    Transaction transaction;

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    transaction = TransactionAdd.inputTransactionInfo();
                    dao.add(transaction);
                    break;
                case "2":
                    transaction = TransactionUpdate.updateTransaction();
                    dao.update(transaction);
                    break;
                case "3":
                    dao.delete(TransactionDelete.deleteTransaction());
                    break;
                case "4":
                    for (Transaction transaction : dao.getAllIncomes()) {
                        System.out.println(transaction);
                    }
                    break;
                case "5":
                    for (Transaction transaction : dao.getAllExpenses()) {
                        System.out.println(transaction);
                    }
                    break;
                case "6":
                    System.out.println("Obecny stan portfela: " + dao.summary());
                    break;
                case "7":
                    dao.close();
                    return;
                default:
                    System.out.println("Wybrano nieprawidlowa opcje");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Co chcesz zrobic?");
        System.out.println("1. Dodać nową transakcję");
        System.out.println("2. Zmodyfikować istniejącą transakcję");
        System.out.println("3. Usunąc transakcję");
        System.out.println("4. Wyświetlenie wszystkich przychodów");
        System.out.println("5. Wyświetlenie wszystkich wydatków");
        System.out.println("6. Obecny stan portfela");
        System.out.println("7. Wyjść z aplikacji");
    }


}
