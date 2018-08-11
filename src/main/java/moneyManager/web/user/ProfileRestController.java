package moneyManager.web.user;

import moneyManager.AuthorizedUser;
import moneyManager.model.User;
import org.springframework.stereotype.Controller;

/**
 * Created by Vladimir on 09.08.2018.
 */
@Controller
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
