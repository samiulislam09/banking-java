public class Account {
    private String account_holder_name;
    private int balance;
    private String accountNumber;

    Account(String account_holder_name, int balance, String accountNumber){
        this.account_holder_name = account_holder_name;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public int getBalance() {
        return balance;
    }
}
