package moneyManager;

import moneyManager.model.Role;
import moneyManager.model.User;
import moneyManager.to.CostWithExceed;
import moneyManager.web.cost.CostRestController;
import moneyManager.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            System.out.println();

            CostRestController controller = appCtx.getBean(CostRestController.class);
            List<CostWithExceed> costsWithExceed = controller.getBetween(
                    LocalDate.of(2018, Month.JULY, 21), LocalTime.of(7, 0),
                    LocalDate.of(2018, Month.JULY, 22), LocalTime.of(12, 0));
            costsWithExceed.forEach(System.out::println);
        }
    }
}
