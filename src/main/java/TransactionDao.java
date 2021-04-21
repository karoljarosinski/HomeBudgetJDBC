import java.sql.*;

public class TransactionDao {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TransactionDao() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/homebudget?serverTimezone=UTC", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Transaction transaction) {
        try {
            String sql = "INSERT INTO homebudget.transaction (type,description,amount,date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType().getDescription());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas dodawania transakcji " + e.getMessage());
        }
    }

    public void update(Transaction transaction) {
        String sql = "UPDATE homebudget.transaction SET type = ?, description = ?, amount = ?, date = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType().getDescription());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.setInt(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM homebudget.transaction WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }
    }

    public void displayAllIncomes() {
        try {
            String sql = "SELECT * FROM homebudget.transaction WHERE type = 'przychód'";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                Transaction transaction = new Transaction(id, TransactionType.INCOME, description, amount, date.toLocalDate());
                System.out.println(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy danych: " + e.getMessage());
        }
    }

    public void displayAllExpenses() {
        try {
            String sql = "SELECT * FROM homebudget.transaction WHERE type = 'wydatek'";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                Transaction transaction = new Transaction(id, TransactionType.EXPENSE, description, amount, date.toLocalDate());
                System.out.println(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy danych: " + e.getMessage());
        }
    }

    public void summary() {
        try {
            String sql = "SELECT sum(amount) FROM homebudget.transaction";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Obecny stan budzetu: " + resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
