import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String input;
        Connection conn = DBconnection.getInstance().getConnection();

        while(true){
            System.out.println("Type 1 for account details\nType 2 for deposit\nType 3 for withdraw\nType 4 for transfer balance\nType 5 for create new account\nType exit to exit");
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")){
                break;
            }

            switch(input){
                case "1":
                    System.out.println("Enter account number: ");
                    input = scanner.nextLine();
                    AccountDetails details = new AccountDetails(input);
                    details.getAccountDetails();

                    break;
                case "2":
                    System.out.println("deposit");
                    break;
                case "3":
                    System.out.println("withdraw");
                    break;
                case "4":
                    System.out.println("transfer balance");
                    break;
                case "5":
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Enter account holder name: ");
                    String account_holder_name = scanner1.nextLine();
                    System.out.println("Enter balance: ");
                    int balance = scanner1.nextInt();
                    CreateAccount createAccount = new CreateAccount(account_holder_name, balance);
                    createAccount.createIntoDb();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}