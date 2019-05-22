package moneyManager.web.user;

import moneyManager.AuthorizedUser;
import moneyManager.model.User;
import moneyManager.to.UserTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Vladimir on 09.08.2018.
 */
@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo) {
        super.update(userTo, AuthorizedUser.id());
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }
}
