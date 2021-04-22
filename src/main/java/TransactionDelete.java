import java.util.Scanner;

public class TransactionDelete {

    public static int deleteTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji którą chcesz usunąć: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
