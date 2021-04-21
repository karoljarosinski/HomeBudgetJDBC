import java.util.Scanner;

public class TransactionUpdate {
    public static Transaction updateTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji która chcesz zaktualizować");
        int id = Integer.parseInt(scanner.nextLine());
        return new Transaction(id, TransactionAdd.addTransaction());
    }
}
