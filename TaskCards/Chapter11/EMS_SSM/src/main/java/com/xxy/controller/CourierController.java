package com.xxy.controller;

import com.xxy.pojo.BootstrapTableCourier;
import com.xxy.pojo.Courier;
import com.xxy.pojo.Message;
import com.xxy.pojo.ResultData;
import com.xxy.service.CourierService;
import com.xxy.util.DateFormatUtil;
import com.xxy.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("courier")
public class CourierController {
    @Resource
    private CourierService courierService;
    private Courier courier;

    @ResponseBody
    @RequestMapping("list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));

        List<Courier> list = courierService.findall(true, offset, pageNumber);
        List<BootstrapTableCourier> list2 = new ArrayList<>();
        for (Courier c : list) {
            String regiterTime = DateFormatUtil.format(c.getRegistertime());
            String loginTime = DateFormatUtil.format(c.getLogintime());
            list2.add(new BootstrapTableCourier(c.getNumber(), c.getCouriername(), c.getCourierphone(), c.getIdcard(), c.getPassword(), c.getCount(), regiterTime, loginTime));
        }
        int total = courierService.gettotal();
        ResultData<BootstrapTableCourier> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        return JsonUtil.toJson(data);
    }

    @ResponseBody
    @RequestMapping("insert.do")
    public String insert(HttpServletRequest req, HttpServletResponse resp) {
        String couriername = req.getParameter("couriername");
        String courierphone = req.getParameter("courierphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");

        int total = courierService.gettotal();
        Courier courier = new Courier(total, couriername, courierphone, idcard, password);
        Boolean flag = courierService.insert(courier);
        Message msg = new Message();
        if (flag) {
            msg.setStatus(0);
            msg.setResult("录入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("录入失败");
        }
        return JsonUtil.toJson(msg);
    }

    @ResponseBody
    @RequestMapping("find.do")
    public String find(HttpServletRequest req, HttpServletResponse resp) {
        String courierphone = req.getParameter("courierphone");
        courier = courierService.findByPhone(courierphone);
        Message msg = new Message();
        if (courier != null) {
            msg.setResult("查找成功");
            msg.setStatus(0);
        } else {
            msg.setResult("查找失败");
            msg.setStatus(-1);
        }
        msg.setData(courier);
        return JsonUtil.toJson(msg);
    }

    @ResponseBody
    @RequestMapping("update.do")
    public String update(HttpServletRequest req, HttpServletResponse resp) {
//        int number = Integer.parseInt(req.getParameter("number"));
        System.out.println("------------old------------");
        System.out.println(courier);
        String couriername = req.getParameter("couriername");
        String courierphone = req.getParameter("courierphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");
        courier.setCouriername(couriername);
        courier.setCourierphone(courierphone);
        courier.setIdcard(idcard);
        courier.setPassword(password);
        System.out.println("------------new------------");
        System.out.println(courier);
        Boolean flag = courierService.update(courier);
        Message msg = new Message();
        if (flag) {
            msg.setResult("修改成功");
            msg.setStatus(0);
        } else {
            msg.setResult("修改失败");
            msg.setStatus(-1);
        }
        return JsonUtil.toJson(msg);
    }
    @ResponseBody
    @RequestMapping("delete.do")
    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        String number = req.getParameter("number");
        Boolean flag = courierService.delete(number);
        Message msg = new Message();
        if (flag) {
            msg.setStatus(0);
            msg.setResult("删除成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        return JsonUtil.toJson(msg);
    }
}
