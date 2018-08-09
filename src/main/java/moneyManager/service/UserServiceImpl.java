package moneyManager.service;

import moneyManager.model.User;
import moneyManager.repository.UserRepository;
import moneyManager.util.exception.NotFoundException;

import java.util.List;

import static moneyManager.util.ValidationUtil.checkNotFound;
import static moneyManager.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Vladimir on 09.08.2018.
 */
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }
}
