package moneyManager;

import moneyManager.util.UserUtil;

import static moneyManager.model.BaseEntity.START_SEQ;

/**
 * Created by Vladimir on 09.08.2018.
 */
public class AuthorizedUser {
    public static int id = START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int getSumPerDay() {
        return UserUtil.DEFAULT_SUM_PER_DAY;
    }
}
