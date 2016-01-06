package ua.vza.vde.qp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.vza.vde.qp.dao.IUserDAO;
import ua.vza.vde.qp.obj.User;

/**
 * Created by SamsungR60P on 29.06.2015.
 */
@RestController
@RequestMapping("/userData")
public class UserRestService {

    @Autowired
    private IUserDAO userDAO;

    @RequestMapping(value = "/getFio", method = RequestMethod.GET)
    public User getUserFioFromLoginRequersParam(@RequestParam(value = "login") String userLogin) {
        User user = userDAO.getUserFioByLogin(userLogin);
        return user;
    }

    @RequestMapping(value = "/getFio/{userLogin}", method = RequestMethod.GET)
    public User getFioFromLoginPathParam(@PathVariable("userLogin") String userLogin) {
        User user = userDAO.getUserFioByLogin(userLogin);
        return user;
    }

}
