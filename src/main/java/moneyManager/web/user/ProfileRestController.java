package moneyManager.web.user;

import moneyManager.AuthorizedUser;
import moneyManager.model.User;

/**
 * Created by Vladimir on 09.08.2018.
 */
public class ProfileRestController extends AbstractUserController {
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    public void update(User user) {
        super.update(user, AuthorizedUser.id());
    }
}
