package ParallelRequests;

public class Bank {
    public final Accounts accounts;

    public Bank(Accounts accounts) {
        this.accounts = accounts;
    }

    public void transfer(long fromId, long toId, double money) {
        Account fromAcc = accounts.findById(fromId);
        Account toAcc = accounts.findById(toId);

        var minAcc = fromAcc.getId() < toAcc.getId() ? fromAcc : toAcc;
        var maxAcc = fromAcc.getId() >= toAcc.getId() ? fromAcc : toAcc;

        synchronized (minAcc) {
            synchronized (maxAcc) {
                if (!fromAcc.hasMoney(money)) {
                    throw new IllegalStateException("Account doesn't have enough money");
                }
                fromAcc.reduceBalance(money);
                toAcc.increaseBalance(money);
            }
        }
    }
}
