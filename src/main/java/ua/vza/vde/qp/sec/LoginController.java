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
 * Created by velenteenko on 24.06.15.
 */

@Controller
public class LoginController {

    @RequestMapping(value = {"/", "/login"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String logInStart(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameUser = authentication.getName();
        String toString = authentication.toString();
        Collection<? extends GrantedAuthority> GrantedAuthorities = authentication.getAuthorities();

        model.addAttribute("GrantedAuthorities", GrantedAuthorities);
        model.addAttribute("username", nameUser);
        model.addAttribute("toString", toString);

        return "login";
    }
}
