package moneyManager.web.cost;

import moneyManager.model.Cost;
import moneyManager.util.DateTimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by Vladimir on 01.01.2019.
 */
@Controller
@RequestMapping(value = "/costs")
public class JspCostController extends AbstractCostController {
    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/costs";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("cost", super.get(getId(request)));
        return "cost";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("cost", new Cost(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000));
        return "cost";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        String id = request.getParameter("id");
        Cost cost = new Cost(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("price")));

        if (cost.isNew()) {
            super.create(cost);
        } else {
            super.update(cost, cost.getId());
        }
        return "redirect:/costs";
    }

    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("costs", super.getBetween(startDate, startTime, endDate, endTime));
        return "costs";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}