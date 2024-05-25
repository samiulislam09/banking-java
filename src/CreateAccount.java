import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.lang.Thread;

public class CreateAccount extends Thread {
    String account_holder_name;
    int balance;

    public CreateAccount(String account_holder_name, int balance){
        this.account_holder_name = account_holder_name;
        this.balance = balance;
    }
    @Override
    public void run(){
        Connection conn = DBconnection.getInstance().getConnection();
        Random random = new Random();
        String accountNumber = String.valueOf(100000 + random.nextInt(900000));
        Account account = new Account(account_holder_name, balance, accountNumber);
        try{
            String query = "INSERT INTO accounts (account_holder_name, balance, accountNumber)" +" VALUES (? , ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, account.getAccount_holder_name());
            stmt.setInt(2, account.getBalance());
            stmt.setString(3, accountNumber);
            System.out.println("Account Created Successfully\nYour account number is: "+accountNumber+" \nPlease remember this for future reference.");
            stmt.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
