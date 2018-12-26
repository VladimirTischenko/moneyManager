package moneyManager.service;

import moneyManager.model.User;
import moneyManager.repository.UserRepository;
import moneyManager.util.ValidationUtil;
import moneyManager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static moneyManager.util.ValidationUtil.checkNotFound;
import static moneyManager.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Vladimir on 09.08.2018.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
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
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
    }

    @Override
    public User getWithCosts(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithCosts(id), id);
    }
}
