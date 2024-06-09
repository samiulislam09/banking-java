import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Thread;

public class Withdraw extends Thread {

    String accountNumber;
    int amount;

    public Withdraw(String accountNumber, int amount){
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public void run(){
            Connection conn = DBconnection.getInstance().getConnection();
            try{
                String query = "Select * from banking.accounts where accountnumber=(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, accountNumber);
                ResultSet result = stmt.executeQuery();
                if(result.next()){
                    int balance = result.getInt("balance");
                    if(balance < amount){
                        System.out.println("Insufficient balance");
                    }
                    else{
                        balance -= amount;
                        String updateQuery = "UPDATE banking.accounts SET balance = ? WHERE accountnumber = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                        updateStmt.setInt(1, balance);
                        updateStmt.setString(2, accountNumber);
                        updateStmt.execute();
                        System.out.println("Amount withdrawn successfully");
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }


}
