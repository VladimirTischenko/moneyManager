package moneyManager.service.datajpa;

import moneyManager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static moneyManager.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}