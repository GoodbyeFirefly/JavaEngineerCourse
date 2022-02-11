package com.xxy.controller;

import com.github.pagehelper.PageInfo;
import com.xxy.domain.User;
import com.xxy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("page")
    public String page(User user,
                       @RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       Model model) {
        PageInfo<User> page = userService.findPage(user, pageNum, pageSize);
        model.addAttribute("page", page);
        return "/user/list";
    }

    @RequestMapping("toadd")
    public String toAdd() {
        return "/user/add";
    }

    @RequestMapping("doadd")
    public String doAdd(User user) {
        userService.add(user);
        return "redirect:/admin/user/page";
    }

    @RequestMapping("toupdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user/update";
    }

    @RequestMapping("doupdate")
    public String doUpdate(User user) {
        userService.update(user);
        return "redirect:/admin/user/page";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin/user/page";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    public String batchDelete(@RequestParam("ids") Integer[] ids) {
        for (Integer id : ids) {
            userService.delete(id);
        }
        return "redirect:/admin/user/page";
    }

}
