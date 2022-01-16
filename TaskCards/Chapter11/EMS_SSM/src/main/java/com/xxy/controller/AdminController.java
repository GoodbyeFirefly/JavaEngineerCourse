package com.xxy.controller;

import com.xxy.pojo.Message;
import com.xxy.service.AdminService;
import com.xxy.util.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("login")
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest req) {
        // 调用service传递参数，并获取结果
        boolean result = AdminService.login(username, password);
        // 根据结果准备不同的返回数据
        Message msg = null;
        if (result) {
            msg = new Message(0, "登录成功");
            Date date = new Date();
            String ip = req.getRemoteAddr();
            AdminService.updateLoginTime(username, date, ip);
            req.getSession().setAttribute("adminUsername", "username");
        } else {
            msg = new Message(-1, "登录失败");
        }
        // 将数据转换为JSON
        String json = JsonUtil.toJson(msg);
        // 将JSON返回给ajax
        return json;
    }
}
