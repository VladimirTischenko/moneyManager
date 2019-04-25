package moneyManager.web;

import moneyManager.AuthorizedUser;
import moneyManager.service.CostService;
import moneyManager.util.CostsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private CostService costService;

    @GetMapping("/")
    public String root() {
        return "redirect:costs";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    @GetMapping("/costs")
    public String costs(Model model) {
        model.addAttribute("costs",
                CostsUtil.getWithExceeded(costService.getAll(AuthorizedUser.id()), AuthorizedUser.getSumPerDay()));
        return "costs";
    }
}
