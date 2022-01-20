package com.xxy.controller;

import com.xxy.pojo.BootstrapTableExpress;
import com.xxy.pojo.Express;
import com.xxy.pojo.Message;
import com.xxy.pojo.ResultData;
import com.xxy.service.ExpressService;
import com.xxy.util.DateFormatUtil;
import com.xxy.util.JsonUtil;
import com.xxy.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("express")
public class ExpressController {
    @Resource
    private ExpressService expressService;

    @ResponseBody
    @RequestMapping("console.do")
    public String console(HttpServletRequest req, HttpServletResponse resp) {
        List<Map<String, Integer>> data = expressService.console();
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

    @ResponseBody
    @RequestMapping("list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        // 1，获取查询数据的起始索引值
        int offset = Integer.parseInt(req.getParameter("offset"));

        // 2，获取当前页要查询的数据量
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));

        // 3，进行查询
        List<Express> list = expressService.findAll(true, offset, pageNumber);
        List<BootstrapTableExpress> list2 = new ArrayList<>();
        for (Express e : list) {
            String inTime = DateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime() == null ? "未出库" : DateFormatUtil.format(e.getOuttime());
            String status = e.getStatus() == 0 ? "待取件" : "已取件";
            String code = e.getCode() == null ? "已取件" : e.getCode();
            list2.add(new BootstrapTableExpress(e.getId(), e.getNumber(), e.getUsername(), e.getUserphone(), e.getCompany(), code, inTime, outTime, status, e.getSysPhone()));

        }
        Integer total = expressService.getTotal();

        // 4，将集合封装为bootstrap-table识别的格式(用于前后端分离)
        ResultData<BootstrapTableExpress> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        String json = JsonUtil.toJson(data);
        return json;
    }

    @ResponseBody
    @RequestMapping("insert.do")
    public String insert (HttpServletRequest req, HttpServletResponse resp) {
        String number = req.getParameter("number");
        String company = req.getParameter("company");
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");

        // 若没有对应的构造方法，可以重新创建一个
        Express e = new Express(number, username, userphone, company, UserUtil.getUserphone(req.getSession()));
        boolean flag = expressService.insert(e);
        Message msg = new Message();
        if (flag) {
            msg.setStatus(0);
            msg.setResult("录入成功！");
        } else {
            // 录入失败只可能是快递单号number重复，与其他三个参数基本无关
            msg.setStatus(-1);
            msg.setResult("录入失败！");
        }
        String json = JsonUtil.toJson(msg);
        return json;
    }

    @ResponseBody
    @RequestMapping("find.do")
    public String find(HttpServletRequest req, HttpServletResponse resp) {
        String number = req.getParameter("number");
        Express e = expressService.findByNumber(number);
        Message msg = new Message();
        if (e == null) {
            msg.setStatus(-1);
            msg.setResult("单号不存在");
        } else {
            msg.setStatus(0);
            msg.setResult("查找成功");
            msg.setData(e);
        }
        String json = JsonUtil.toJson(msg);
        return json;
    }

    @ResponseBody
    @RequestMapping("update.do")
    public String update(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String number = req.getParameter("number");
        String company = req.getParameter("company");
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");
        int status = Integer.parseInt(req.getParameter("status"));

        Express e = new Express();
        e.setId(id);
        e.setNumber(number);
        e.setCompany(company);
        e.setUsername(username);
        e.setUserphone(userphone);
        e.setStatus(status);
        boolean update = expressService.update(e);
        Message msg = new Message();
        if (update) {
            msg.setStatus(0);
            msg.setResult("修改成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        String json = JsonUtil.toJson(msg);
        return json;
    }

    @ResponseBody
    @RequestMapping("delete.do")
    public String delete(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = expressService.delete(id);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("删除成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        String json = JsonUtil.toJson(msg);
        return json;
    }

}
