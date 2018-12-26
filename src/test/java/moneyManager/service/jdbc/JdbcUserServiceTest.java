package moneyManager.service.jdbc;

import moneyManager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static moneyManager.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}