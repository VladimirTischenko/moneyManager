package moneyManager.repository;

import moneyManager.model.User;

import java.util.List;

/**
 * Created by Vladimir on 09.08.2018.
 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default User getWithCosts(int id){
        throw new UnsupportedOperationException();
    }
}
