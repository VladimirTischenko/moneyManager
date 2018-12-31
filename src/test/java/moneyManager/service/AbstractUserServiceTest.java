package moneyManager.service;

import moneyManager.model.Role;
import moneyManager.model.User;
import moneyManager.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static moneyManager.UserTestData.*;

public abstract class AbstractUserServiceTest extends AbstractServiceTest{
    @Autowired
    protected UserService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void testSave() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, Collections.singleton(Role.ROLE_USER));
        User created = service.save(newUser);
        newUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() {
        service.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void testDelete() {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(1);
    }

    @Test
    public void testGet() {
        User user = service.get(USER_ID);
        MATCHER.assertEquals(USER, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        service.get(1);
    }

    @Test
    public void testGetByEmail() {
        User user = service.getByEmail("user@yandex.ru");
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void testGetAll() {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void testUpdate() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setSumPerDay(330);
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(USER_ID));
    }
}
