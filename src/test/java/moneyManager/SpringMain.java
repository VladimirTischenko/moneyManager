package moneyManager;

import moneyManager.model.User;
import moneyManager.to.CostWithExceed;
import moneyManager.web.cost.CostRestController;
import moneyManager.web.user.AdminRestController;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vladimir on 11.08.2018.
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.DB_IMPLEMENTATION);
            appCtx.load("spring/spring-app.xml", "spring/mock.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);

            List<User> users = adminUserController.getAll();
            System.out.println(users);

            CostRestController controller = appCtx.getBean(CostRestController.class);
            List<CostWithExceed> costsWithExceed = controller.getBetween(
                    LocalDate.of(2018, Month.JULY, 21), LocalTime.of(7, 0),
                    LocalDate.of(2018, Month.JULY, 22), LocalTime.of(12, 0));
            costsWithExceed.forEach(System.out::println);
        }
    }
}
