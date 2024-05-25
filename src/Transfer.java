import java.lang.Thread;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer extends Thread{
    String fromAccountNumber;
    String toAccountNumber;
    int amount;
    int senderId;
    int receiverId;

    Transfer(String fromAccountNumber, String toAccountNumber, int amount){
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    @Override
    public void run(){
        Connection conn = DBconnection.getInstance().getConnection();
        String getDataQuery = "Select * from banking_system.accounts where accountNumber=(?)";
        String updateQuery = "UPDATE banking_system.accounts SET balance = ? WHERE accountNumber=(?)";
        try {
            PreparedStatement fromAccountStmt = conn.prepareStatement(getDataQuery);
            fromAccountStmt.setString(1, fromAccountNumber);
            ResultSet fromAccountResult = fromAccountStmt.executeQuery();
            if(fromAccountResult.next()){
                int balance = fromAccountResult.getInt("balance");
                this.senderId = fromAccountResult.getInt("account_id");
                balance -= amount;
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, balance);
                updateStmt.setString(2, fromAccountNumber);
                updateStmt.execute();
                UpdateTransaction upTransaction = new UpdateTransaction(senderId, "Transfer", amount);
                upTransaction.storeTransactionData();

            }
            getDataQuery = "Select * from banking_system.accounts where accountNumber=(?)";
            PreparedStatement toAccountStmt = conn.prepareStatement(getDataQuery);
            toAccountStmt.setString(1, toAccountNumber);
            ResultSet toAccountResult = toAccountStmt.executeQuery();
            if(toAccountResult.next()){
                int balance = fromAccountResult.getInt("balance");
                this.receiverId = fromAccountResult.getInt("account_id");
                balance += amount;
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, balance);
                updateStmt.setString(2, toAccountNumber);
                updateStmt.execute();
                UpdateTransaction receiveTransaction = new UpdateTransaction(receiverId, "Received", amount);
                receiveTransaction.storeTransactionData();

            }
            System.out.println("Transfer money successful");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
