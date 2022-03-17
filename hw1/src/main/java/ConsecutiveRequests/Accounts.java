package ConsecutiveRequests;

import java.util.HashMap;
import java.util.Map;

public class Accounts {
    private final Map<Long, Account> accountsById = new HashMap<>();

    public Account findById(long fromId) {
        return accountsById.computeIfAbsent(fromId, (k)-> new Account(k, 100));
    }
}
