import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UpdateTransaction {
    int accountId;
    String transactionType;
    int amount;
    UpdateTransaction(int accountId, String transactionType, int amount){
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public void storeTransactionData(){
        Connection conn = DBconnection.getInstance().getConnection();
        try{
            String query = "INSERT INTO banking.transactions (account_id, transaction_type, amount) VALUES (? , ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accountId);
            stmt.setString(2, transactionType);
            stmt.setInt(3, amount);
            stmt.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
