package ua.vza.vde.qp.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;

/**
 * Created by velenteenko on 23.06.15.
 */

@Controller
public class HomeController {

    @Autowired
    ApplicationContext context;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String homeSetUp(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "index";

    }

    @RequestMapping(value = "/403page", method = RequestMethod.GET)
    public ModelAndView accessDenided(Principal user) {
        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Здравствуйте, " + user.getName() + " Вы не имеете права просматривать эту страницу!");
        } else {
            model.addObject("msg", "Вы не имеете права просматривать эту страницу!");
        }

        model.addObject("logIn", "Войти");
        model.setViewName("403page");

        return model;
    }

    @RequestMapping(value = "/404page", method = RequestMethod.GET)
    public ModelAndView notFound(Principal user) {
        ModelAndView model = new ModelAndView();

        model.addObject("msg", "404. :( Извените, страница не найдена или не существует.");

        model.setViewName("404page");

        return model;
    }
}
