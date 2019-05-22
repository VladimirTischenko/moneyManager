package moneyManager.web.cost;

import moneyManager.web.LocalExceptionInfoHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(assignableTypes = AbstractCostController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CostExceptionInfoHandler extends LocalExceptionInfoHandler {

    public CostExceptionInfoHandler() {
        super("exception.costs.duplicate_datetime");
    }
}
