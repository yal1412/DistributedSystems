package ParallelRequests;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Accounts {
    private final Map<Long, Account> accountsById = new ConcurrentHashMap<>();

    public Account findById(long fromId) {
        return accountsById.computeIfAbsent(fromId, (k)-> new Account(k, 100));
    }
}
