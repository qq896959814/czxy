package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.domain.Category;
import com.czxy.service.CategoryService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends BaseServlet {

    /**
     * 获取所有分类的信息,并请求转发到jsp/index.jsp
     * @return
     */
    public void getCategory() throws IOException {
        CategoryService service = new CategoryService();
        List<Category> categories = service.findAll();
        JSONArray jsonArray = JSONArray.fromObject(categories);
        getResponse().getWriter().write(jsonArray.toString());
    }
}
