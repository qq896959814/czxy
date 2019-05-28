package com.czxy.bookstore.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.bookstore.domain.User;
import com.czxy.bookstore.service.UserService;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;


public class UserServlet extends BaseServlet {

    //创建用户serivce，为servlet提供功能操作
    private UserService service = new UserService();

    //测试跑通环境，在浏览器直接输入URL测试即可
    public void testFindAll() throws IOException {
        List<User> allUser = service.findAll();
        System.out.println(allUser);
    }

    /**
     * 根据用户名进行校验
     */
    public void checkUsername() throws IOException {
        //1.获取用户输入的用户名
//        String username = getRequest().getParameter("username");//获取单个的用户名
//        直接将用户名tobean成User对象,方便mybatis操作
        User user = toBean(User.class);
        //2.调用service，对该用户名进行校验，得到一个该用户名的用户对象
        User exUser = service.findByUsername(user);
        //3.判断该对象是否为空
        //3.1如果为空，写出1
        if (exUser == null) {
            getResponse().getWriter().write("1");
        } else {
            //3.2如果不为空，写出0
            getResponse().getWriter().write("0");
        }
    }

    /**
     * 登陆方法
     *
     * @return
     */
    public String login() throws IOException {

        //1.获取用户数据
        User user = toBean(User.class);
        //2.使用service完成登陆动作
        User loginUser = service.login(user);
        //3.判断登陆动作返回的用户对象
        if (loginUser == null) {
            //3.1如果没有对象,登陆失败,将错误信息放入request中,并请求转发到login.jsp
            getRequest().setAttribute("message", "账号或密码错误!");
            return "jsp/login.jsp";
        } else {
            //3.2如果有对象,登陆成功,将登陆用户对象放到session,以便后续从session中获取用户数据
            getSession().setAttribute("loginUser", loginUser);
            //记住我：登陆成功后，判断用户是否要记住我
            String rememberMe = getRequest().getParameter("rememberMe");
            //创建Cookie对象
            Cookie cookie = new Cookie("rememberCookie", "on");
            Cookie cookie2 = new Cookie("lastUsername", loginUser.getUsername());
            Cookie cookie3 = new Cookie("lastPassword", loginUser.getPassword());
            //如果用户不需要记住我，则将cookie全部设置成销毁
            if (rememberMe == null) {
                cookie.setMaxAge(0);
                cookie2.setMaxAge(0);
                cookie3.setMaxAge(0);
            }
            //无论是否记住我，都需要向客户端发送cookie
            getResponse().addCookie(cookie);

            getResponse().addCookie(cookie2);
            getResponse().addCookie(cookie3);

            //如果直接跳转jsp/index.jsp,则request中就没有了热门商品和最新商品，就读取不到了，登出同理
//            return "redirect:/IndexServlet?method=showIndex";
            return "redirect:/index.jsp";
        }
    }

    /**
     * 登出
     *
     * @return
     */
    public String logout() {

        //将session中的loginUser登陆用户直接删除掉
        getSession().removeAttribute("loginUser");

        //消除该用户对应session的所有内容
        getSession().invalidate();

        //无论在哪里都跳转到首页
        //return "redirect:/IndexServlet?method=showIndex";
        return "redirect:/index.jsp";
    }
}
