package com.xxy.controller;

import com.github.pagehelper.PageInfo;
import com.xxy.domain.Seller;
import com.xxy.domain.User;
import com.xxy.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/seller/")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @RequestMapping("page")
    public String page(Seller seller,
                       @RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       Model model) {
        PageInfo<Seller> page = sellerService.findPage(seller, pageNum, pageSize);
        model.addAttribute("page", page);
        return "seller/list";
    }

    @RequestMapping("toadd")
    public String toAdd() {
        return "seller/add";
    }

    @RequestMapping("doadd")
    public String doAdd(Seller seller) {
        sellerService.add(seller);
        return "redirect:/admin/seller/page";
    }

    @RequestMapping("toupdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        Seller seller = sellerService.findById(id);
        model.addAttribute("seller", seller);
        return "seller/update";
    }

    @RequestMapping("doupdate")
    public String doUpdate(Seller seller) {
        sellerService.update(seller);
        return "redirect:/admin/seller/page";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        sellerService.delete(id);
        return "redirect:/admin/seller/page";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    public String batchDelete(@RequestParam("ids") Integer[] ids) {
        for (Integer id : ids) {
            sellerService.delete(id);
        }
        return "redirect:/admin/seller/page";
    }
}
