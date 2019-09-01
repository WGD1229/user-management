package my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Guodong
 * @title: MainController
 * @projectName myshop
 * @description: TODO
 * @date 2019/8/15 13:49
 */

@Controller
public class MainController {
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
