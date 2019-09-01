package my.shop.web.admin.web.controller;


import my.shop.commons.constant.ConstantUtils;

import my.shop.commons.utils.CookieUtils;
import my.shop.domain.TbUser;
import my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/loginController")
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = {"","login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest, Model model){
        String userInfo = CookieUtils.getCookieValue(httpServletRequest,"userInfo");
        if (!StringUtils.isBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            String email =  userInfoArray[0];
            String password = userInfoArray[1];
            model.addAttribute("email",email);
            model.addAttribute("password",password);
            model.addAttribute("isRemember",true);
        }
        else model.addAttribute("isRemember",false);
    return "login";

    }

    /**
     * post方式
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true)String password,
                        String flagRemember,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse,
                        Model model
                        ){


        boolean isRemember = flagRemember != null;
        if (!isRemember){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,
                    "userInfo");
        }

        TbUser tbUser = tbUserService.login(email, password);

        if (tbUser == null){
            model.addAttribute("message","账户名或密码错误");
            return "login";
        }
        else {

            if (isRemember){
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,
                        "userInfo",
                        String.format("%s:%s",email,password),
                        7 * 24 * 60 * 60);
            }

            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }

    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "redirect:/login";

    }

}
