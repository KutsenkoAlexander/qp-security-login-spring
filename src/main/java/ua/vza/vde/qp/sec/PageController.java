package ua.vza.vde.qp.sec;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by velenteenko on 03.07.15.
 */

@Controller
public class PageController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allProduct(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "all";

    }

    @RequestMapping(value = "/himiya", method = RequestMethod.GET)
    public String chemistry(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "chemistry";

    }

    @RequestMapping(value = "/complect", method = RequestMethod.GET)
    public String complect(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "complect";

    }

    @RequestMapping(value = "/metal", method = RequestMethod.GET)
    public String metall(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "metall";

    }

    @RequestMapping(value = "/metiz", method = RequestMethod.GET)
    public String metiz(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "metiz";

    }

    @RequestMapping(value = "/nemetal", method = RequestMethod.GET)
    public String nometal(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "nometall";

    }

    @RequestMapping(value = "/setkaLentaProvoloka", method = RequestMethod.GET)
    public String setkaLentaProvoloka(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "setka";

    }

}
