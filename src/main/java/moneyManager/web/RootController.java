package moneyManager.web;

import moneyManager.AuthorizedUser;
import moneyManager.service.CostService;
import moneyManager.service.UserService;
import moneyManager.util.CostsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private CostService costService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:costs";
    }

    @RequestMapping(value = "/costs", method = RequestMethod.GET)
    public String costs(Model model) {
        model.addAttribute("costs",
                CostsUtil.getWithExceeded(costService.getAll(AuthorizedUser.id()), AuthorizedUser.getSumPerDay()));
        return "costs";
    }
}
