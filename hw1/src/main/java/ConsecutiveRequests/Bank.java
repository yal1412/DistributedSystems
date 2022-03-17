package ConsecutiveRequests;

public class Bank {
    public final Accounts accounts;

    public Bank(Accounts accounts) {
        this.accounts = accounts;
    }

    public void transfer(long fromId, long toId, double money) {
        Account fromAcc = accounts.findById(fromId);
        Account toAcc = accounts.findById(toId);

        if (!fromAcc.hasMoney(money)) {
            throw new IllegalStateException("ConsecutiveRequests.Account doesn't have enough money");
        }
        fromAcc.reduceBalance(money);
        toAcc.increaseBalance(money);
    }
}
