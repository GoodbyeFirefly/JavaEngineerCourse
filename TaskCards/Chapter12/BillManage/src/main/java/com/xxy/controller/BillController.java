package com.xxy.controller;

import com.xxy.entity.Bill;
import com.xxy.entity.BillType;
import com.xxy.service.BillService;
import com.xxy.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("bill")
public class BillController {
    @Resource
    private BillService billService;
    @Resource
    private TypeService typeService;

    @RequestMapping("list")
    public String list(Bill bill, Model model) {
        List<BillType> types = typeService.selectAll();
        model.addAttribute("types", types);
        List<Bill> bills = billService.list(bill);
        model.addAttribute("bills", bills);
        model.addAttribute("bill", bill);
        return "/bill/list";
    }

    @RequestMapping("toAddPage")
    public String toAdd(Model model) {
        List<BillType> billTypes = typeService.selectAll();
        model.addAttribute("types", billTypes);
        return "/bill/add";
    }

    @RequestMapping("add.do")
    public String add(Bill bill) {
        billService.insert(bill);
        return "redirect:/bill/list";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        billService.delete(id);
        return "redirect:/bill/list";
    }

    @RequestMapping("toUpdatePage/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        List<BillType> billTypes = typeService.selectAll();
        model.addAttribute("types", billTypes);
        Bill bill = billService.selectByPrimaryKey(id);
        model.addAttribute("bill", bill);
        return "/bill/update";
    }

    @RequestMapping("update.do")
    public String update(Bill bill) {
        billService.updateByPrimaryKey(bill);
        return "redirect:/bill/list";
    }
}
