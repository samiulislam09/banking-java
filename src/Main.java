import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Creating account
        CreateAccount createAccount1 = new CreateAccount("john", 1000);
        createAccount1.start();
        CreateAccount createAccount2 = new CreateAccount("doe", 1000);
        createAccount2.start();
        CreateAccount createAccount3 = new CreateAccount("jack", 1000);
        createAccount3.start();

        // main program begins here
        Scanner scanner = new Scanner(System.in);
        String input;
        while(true){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Type 1 for account details\nType 2 for deposit\nType 3 for withdraw\nType 4 for transfer balance\nType 5 for create new account\nType exit to exit");
                }
            }, 700);

            input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")){
                break;
            }

            switch(input){
                case "1":
                    System.out.println("Enter account number: ");
                    input = scanner.nextLine();
                    AccountDetails details = new AccountDetails(input);
                    details.start();
                    break;

                case "2":
                    System.out.println("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.println("Enter deposit amount: ");
                    input = scanner.nextLine();
                    int amount = Integer.parseInt(input);
                    Deposit deposit = new Deposit(accountNumber, amount);
                    deposit.start();
                    break;

                case "3":
                    System.out.println("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.println("Enter withdraw amount: ");
                    input = scanner.nextLine();
                    amount = Integer.parseInt(input);
                    Withdraw withdraw = new Withdraw(accountNumber, amount);
                    withdraw.start();
                    break;

                case "4":
                    System.out.println("Enter sender Account Number: ");
                    String senderAcNumber = scanner.nextLine();
                    System.out.println("Enter Receiver Account Number: ");
                    String receiverAcNumber = scanner.nextLine();
                    System.out.println("Enter Amount To be Transfer: ");
                    int amountToTransfer = scanner.nextInt();
                    Transfer transfer = new Transfer(senderAcNumber, receiverAcNumber, amountToTransfer);
                    transfer.start();
                    break;

                case "5":
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Enter account holder name: ");
                    String account_holder_name = scanner1.nextLine();
                    System.out.println("Enter balance: ");
                    int balance = scanner1.nextInt();
                    CreateAccount createAccount = new CreateAccount(account_holder_name, balance);
                    createAccount.start();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}