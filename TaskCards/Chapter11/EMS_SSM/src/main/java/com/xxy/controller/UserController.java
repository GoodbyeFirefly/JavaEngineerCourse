package com.xxy.controller;

import com.xxy.pojo.BootstrapTableUser;
import com.xxy.pojo.Message;
import com.xxy.pojo.ResultData;
import com.xxy.pojo.User;
import com.xxy.service.UserService;
import com.xxy.util.DateFormatUtil;
import com.xxy.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    private User user;

    @ResponseBody
    @RequestMapping("list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        List<User> list = userService.findAll(true, offset, pageNumber);
        List<BootstrapTableUser> list2 = new ArrayList<>();
        for (User u : list) {
            String registertime = DateFormatUtil.format(u.getRegistertime());
            String logintime = DateFormatUtil.format(u.getLogintime());
            list2.add(new BootstrapTableUser(u.getNumber(), u.getUsername(), u.getUserphone(), u.getIdcard(), u.getPassword(), registertime, logintime));
        }
        int total = userService.getTotal();
        ResultData<BootstrapTableUser> data = new ResultData();
        data.setTotal(total);
        data.setRows(list2);
        return JsonUtil.toJson(data);
    }

    @ResponseBody
    @RequestMapping("insert.do")
    public String insert(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");
        Boolean result = userService.insert(new User(username, userphone, idcard, password));
        Message msg = new Message();
        if (result) {
            msg.setStatus(0);
            msg.setResult("插入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("插入失败");
        }
        return JsonUtil.toJson(msg);
    }

    @ResponseBody
    @RequestMapping("find.do")
    public String find(HttpServletRequest req, HttpServletResponse resp) {
        String userphone = req.getParameter("userphone");
        user = userService.findByPhone(userphone);
        Message msg = new Message();
        if (user != null) {
            msg.setStatus(0);
            msg.setResult("查找成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("查找失败");
        }
        msg.setData(user);
        return JsonUtil.toJson(msg);
    }

    @ResponseBody
    @RequestMapping("update.do")
    public String update(HttpServletRequest req, HttpServletResponse response) {
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");
        int number = Integer.parseInt(req.getParameter("number"));
        user.setUsername(username);
        user.setUserphone(userphone);
        user.setIdcard(idcard);
        user.setPassword(password);
        user.setNumber(number);
        Boolean result = userService.update(user);
        Message msg = new Message();
        if (result) {
            msg.setStatus(0);
            msg.setResult("修改成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        return JsonUtil.toJson(msg);
    }

    @ResponseBody
    @RequestMapping("delete.do")
    public String delete(HttpServletRequest req, HttpServletResponse response) {
        String number = req.getParameter("number");
        Boolean result = userService.delete(number);
        Message msg = new Message();
        if (result) {
            msg.setStatus(0);
            msg.setResult("删除成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        return JsonUtil.toJson(msg);
    }
}
