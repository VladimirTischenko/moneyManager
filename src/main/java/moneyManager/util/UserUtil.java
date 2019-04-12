package moneyManager.util;

import moneyManager.model.Role;
import moneyManager.model.User;
import moneyManager.to.UserTo;

/**
 * GKislin
 */
public class UserUtil {
    public static final int DEFAULT_SUM_PER_DAY = 2000;

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }
}
