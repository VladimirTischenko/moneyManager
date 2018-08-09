package moneyManager;

import moneyManager.util.CostsUtil;

/**
 * Created by Vladimir on 09.08.2018.
 */
public class AuthorizedUser {

    public static int id() {
        return 1;
    }

    public static int getSumPerDay() {
        return CostsUtil.DEFAULT_SUM_PER_DAY;
    }
}
