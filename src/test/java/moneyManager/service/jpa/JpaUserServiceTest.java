package moneyManager.service.jpa;

import moneyManager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static moneyManager.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractUserServiceTest {
}