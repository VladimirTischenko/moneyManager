package moneyManager.web.cost;

import moneyManager.model.Cost;
import moneyManager.to.CostWithExceed;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vladimir on 01.01.2019.
 */
@RestController
@RequestMapping(value = "/ajax/profile/costs")
public class CostAjaxController extends AbstractCostController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CostWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public Cost get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void updateOrCreate(@Valid Cost cost) {
        if (cost.isNew()) {
            super.create(cost);
        } else {
            super.update(cost, cost.getId());
        }
    }

    @Override
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CostWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
