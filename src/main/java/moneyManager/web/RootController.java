package moneyManager.web;

import moneyManager.AuthorizedUser;
import moneyManager.service.CostService;
import moneyManager.util.CostsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:costs";
    }

    @GetMapping("/costs")
    public String costs(Model model) {
        model.addAttribute("costs",
                CostsUtil.getWithExceeded(costService.getAll(AuthorizedUser.id()), AuthorizedUser.getSumPerDay()));
        return "costs";
    }
}
