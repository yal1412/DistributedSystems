package ConsecutiveRequests;

public class Account {
    private final long id;
    private double balance;

    public Account(long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasMoney(double money) {
        return balance >= money;
    }

    public void increaseBalance(double money) {
        balance += money;
    }

    public void reduceBalance(double money) {
        balance -= money;
    }
}
