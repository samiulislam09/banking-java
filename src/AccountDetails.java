import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Thread;

public class AccountDetails extends Thread {
    private String accountNumber;

    public AccountDetails(String accountNumber){
        this.accountNumber = accountNumber;
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
                System.out.println("Account Holder Name: "+result.getString("account_holder_name"));
                System.out.println("Account Number: "+result.getString("accountNumber"));
                System.out.println("Balance: "+result.getInt("balance"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


}
