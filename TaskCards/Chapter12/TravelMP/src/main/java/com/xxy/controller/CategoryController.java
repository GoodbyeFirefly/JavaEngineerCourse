package com.xxy.controller;

import com.xxy.domain.Category;
import com.xxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("list")
    public String find(Model model) {
        List<Category> list = categoryService.find();
        model.addAttribute("list", list);
        return "category/list";
    }

    @RequestMapping("toadd")
    public String toAdd() {
        return "category/add";
    }

    @RequestMapping("doadd")
    public String doAdd(Category category) {
        categoryService.add(category);
        return "redirect:/admin/category/list";
    }

    @RequestMapping("toupdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/update";
    }

    @RequestMapping("doupdate")
    public String doUpdate(Category category) {
        categoryService.update(category);
        return "redirect:/admin/category/list";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return "redirect:/admin/category/list";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    public String batchDelete(@RequestParam("ids") Integer[] ids) {
        for (Integer id : ids) {
            categoryService.delete(id);
        }
        return "redirect:/admin/category/list";
    }

}
