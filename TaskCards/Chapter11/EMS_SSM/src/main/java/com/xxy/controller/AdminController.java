package com.xxy.controller;

import com.xxy.pojo.Courier;
import com.xxy.pojo.Message;
import com.xxy.service.AdminService;
import com.xxy.service.CourierService;
import com.xxy.service.ExpressService;
import com.xxy.service.UserService;
import com.xxy.util.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("admin")
@Controller
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private ExpressService expressService;
    @Resource
    private CourierService courierService;
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("login.do")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest req) {
        // 调用service传递参数，并获取结果
        boolean result = adminService.login(username, password);
        // 根据结果准备不同的返回数据
        Message msg = null;
        if (result) {
            msg = new Message(0, "登录成功");
            Date date = new Date();
            String ip = req.getRemoteAddr();
            adminService.updateLoginTime(username, date, ip);
            req.getSession().setAttribute("adminUsername", "username");
        } else {
            msg = new Message(-1, "登录失败");
        }
        // 将数据转换为JSON
        String json = JsonUtil.toJson(msg);
        // 将JSON返回给ajax
        return json;
    }
    @RequestMapping("logout.do")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "/admin/login";
    }

    @RequestMapping("console.do")
    @ResponseBody
    public String console(HttpServletRequest req, HttpServletResponse resp) {
        List<Map<String, Integer>> data = expressService.console();
        data.add(userService.console());
        data.add(courierService.console());
        Message msg = new Message();
        if (data.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
        }
        msg.setData(data);
        String json = JsonUtil.toJson(msg);
        return json;
    }
}
