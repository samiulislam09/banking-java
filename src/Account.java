public class Account {
    private String account_holder_name;
    private int balance;
    private String accountNumber;

    Account(String account_holder_name, int balance, String accountNumber){
        this.account_holder_name = account_holder_name;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public int getBalance() {
        return balance;
    }
}
