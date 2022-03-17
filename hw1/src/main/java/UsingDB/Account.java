import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Account {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private final long id;

    @Getter
    @Setter
    private double balance;
    
    @Version
    private long version;

    public Account() {
        this.id = 0;
        this.balance = 0;
    }

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
        this.setBalance(balance + money);
    }

    public void reduceBalance(double money) {
        this.setBalance(balance - money);
    }
}
