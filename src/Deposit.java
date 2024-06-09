import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Thread;

public class Deposit extends Thread {
    String accountNumber;
    int amount;

    public Deposit(String accountNumber, int amount){
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public void run() {
        Connection conn = DBconnection.getInstance().getConnection();
        String query = "Select * from banking.accounts where accountnumber=(?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, accountNumber);
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                int balance = result.getInt("balance");
                balance += amount;
                String updateQuery = "UPDATE banking.accounts SET balance = ? WHERE accountnumber = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, balance);
                updateStmt.setString(2, accountNumber);
                updateStmt.execute();
                int intAccountNumber = Integer.parseInt(accountNumber);
                UpdateTransaction upTransaction = new UpdateTransaction(intAccountNumber, "Deposit", amount);
                upTransaction.storeTransactionData();
                System.out.println("Amount deposited successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
