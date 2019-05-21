package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.vcode.VerifyCode;
import com.czxy.domain.User;
import com.czxy.service.UserService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends BaseServlet {
    private UserService us = new UserService();

    public String login() throws IOException {
        HttpServletRequest request = getRequest();
        String remeberMe = request.getParameter("remeberMe");
        String autoLogin = request.getParameter("autoLogin");
        User user = toBean(User.class);
        User login = us.login(user);
        if (login != null){
            getSession().setAttribute("loginUser",login);
            if (remeberMe != null && remeberMe!=""){
                Cookie username = new Cookie("username", login.getUsername());
                Cookie password = new Cookie("password", login.getPassword());
                
            }
            return "redirect:/index.jsp";
        }
        getSession().setAttribute("msg","账号或密码错误");
        return "redirect:/jsp/login.jsp";
    }

    public String logout(){
        return "";
    }


    public void getImg() throws IOException {
        VerifyCode code = new VerifyCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        getSession().setAttribute("verify",text);
        ServletOutputStream stream = getResponse().getOutputStream();
        VerifyCode.output(image,stream);
    }

    public void getVerify() throws IOException {
        String verify = getRequest().getParameter("verify");
        String attribute = (String) getSession().getAttribute("verify");
        Map<String,Object> map = new HashMap<>();
        if (verify.equalsIgnoreCase(attribute)){
            map.put("code",true);
        }else {
            map.put("code",false);
        }
        String s = JSONObject.fromObject(map).toString();
        getResponse().getWriter().write(s);
    }
}
