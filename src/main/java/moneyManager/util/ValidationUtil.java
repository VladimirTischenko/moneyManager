package moneyManager.util;

import moneyManager.util.exception.NotFoundException;

/**
 * Created by Vladimir on 09.08.2018.
 */
public class ValidationUtil {
    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) throw new NotFoundException("Not found entity with " + msg);
    }
}
