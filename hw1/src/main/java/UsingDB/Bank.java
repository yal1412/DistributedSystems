import javax.persistence.*;

public class Bank {
    public final Accounts accounts;
    private final EntityManagerFactory entityManagerFactory;

    public Bank(Accounts accounts) {
        this.accounts = accounts;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.easyjava.data.jpa.hibernate");
    }

    public void transfer(long fromId, long toId, double money) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Account fromAcc = entityManager.find(Account.class, fromId, LockModeType.OPTIMISTIC);
            Account toAcc = entityManager.find(Account.class, toId, LockModeType.OPTIMISTIC);

            if (!fromAcc.hasMoney(money)) {
                throw new IllegalStateException("Account doesn't have enough money");
            }

            entityManager.lock(fromAcc, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            fromAcc.reduceBalance(money);
            entityManager.getTransaction().commit();

            entityManager.lock(toAcc, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            toAcc.increaseBalance(money);
            entityManager.getTransaction().commit();

            entityManager.close();
        } catch (RollbackException ex) {
            System.out.println("Rollback Exception");
        }
    }
}
