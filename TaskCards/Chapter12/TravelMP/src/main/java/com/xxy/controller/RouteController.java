package com.xxy.controller;

import com.github.pagehelper.PageInfo;
import com.xxy.domain.Category;
import com.xxy.domain.Route;
import com.xxy.domain.RouteImg;
import com.xxy.domain.Seller;
import com.xxy.service.CategoryService;
import com.xxy.service.RouteImgService;
import com.xxy.service.RouteService;
import com.xxy.service.SellerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/route")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private RouteImgService routeImgService;

    @RequestMapping("page")
    public String page(Route route,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Model model) {
        PageInfo<Route> page = routeService.findPage(route, pageNum, pageSize);
        model.addAttribute("page", page);
        //查询所有分类生成下拉框
        List<Category> categories = categoryService.find();
        model.addAttribute("categories", categories);
        List<Seller> sellers = sellerService.find(new Seller());
        model.addAttribute("sellers", sellers);
        //用于页面回显
        model.addAttribute("route", route);
        return "route/list";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id") Integer id) {
        routeService.delete(id);
        return "redirect:/admin/route/page";
    }

    @RequestMapping("toadd")
    public String toAdd(Model model) {
        //查询所有分类生成下拉框
        List<Category> categories = categoryService.find();
        model.addAttribute("categories", categories);
        List<Seller> sellers = sellerService.find(new Seller());
        model.addAttribute("sellers", sellers);
        return "route/add";
    }

    @RequestMapping("doadd")
    public String doAdd(Route route, @RequestParam("rimageFile") MultipartFile rimageFile, HttpServletRequest request) throws IOException {
        performRImage(route, rimageFile, request);
        routeService.add(route);
        return "redirect:/admin/route/page";
    }

    @RequestMapping("toupdate")
    public String toUpdate(@RequestParam("id") Integer id, Model model) {
        //查询所有分类生成下拉框
        List<Category> categories = categoryService.find();
        model.addAttribute("categories", categories);
        List<Seller> sellers = sellerService.find(new Seller());
        model.addAttribute("sellers", sellers);
        Route route = routeService.findById(id);

        System.out.println("获取到的route：");
        System.out.println(route);

        model.addAttribute("route", route);
        return "route/update";
    }

    @RequestMapping("/doupdate")
    public String doUpdate(Route route, @RequestParam("rimageFile") MultipartFile rimageFile, HttpServletRequest request) throws IOException {
        performRImage(route, rimageFile, request);
        routeService.update(route);
        return "redirect:/admin/route/page";
    }

    @RequestMapping("toimage")
    public String toImage(@RequestParam("id") Integer id, Model model) {
        Route route = routeService.findById(id);
        model.addAttribute("route", route);
        return "route/image";
    }

    @RequestMapping("/doimage")
    public String doImage(Integer rid,
                          @RequestParam("bigPicFile") MultipartFile[] bigPicFile,
                          @RequestParam("smallPicFile")MultipartFile[] smallPicFile,
                          HttpServletRequest request) throws Exception {
        List<String> bigPic = new ArrayList<>();
        List<String> smallPic = new ArrayList<>();
        String path = request.getServletContext().getRealPath("/");
        for (MultipartFile f : bigPicFile) {
            File bigPath = new File(path + "img\\product\\big-pic\\");
            if (!bigPath.exists()) {
                bigPath.mkdirs();
            }
            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + FilenameUtils.getExtension(f.getOriginalFilename());
            f.transferTo(new File(bigPath, fileName));
            bigPic.add("img/product/big-pic/" + fileName);
        }

        for (MultipartFile f : smallPicFile) {
            File smallPath = new File(path + "img\\product\\small-pic\\");
            if (!smallPath.exists()) {smallPath.mkdirs();
            }
            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + FilenameUtils.getExtension(f.getOriginalFilename());
            f.transferTo(new File(smallPath, fileName));
            smallPic.add("img/product/small-pic/" + fileName);
        }
        //要添加的图片列表
        List<RouteImg> ris = new ArrayList<>();
        for (int i=0; i<bigPic.size(); i++) {
            RouteImg img = new RouteImg();
            img.setRid(rid);
            img.setBigpic(bigPic.get(i));
            img.setSmallpic(smallPic.get(i));
            ris.add(img);
        }
        routeImgService.saveImg(rid, ris);
        return "redirect:/admin/route/page";
    }

    private void performRImage(Route route, @RequestParam("rimageFile") MultipartFile rimageFile, HttpServletRequest request) throws IOException {
        // 项目的部署目录 + img/product/rimage
        String savePath = request.getServletContext().getRealPath("img/product/rimage/");

        // 保存的文件名称
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(rimageFile.getOriginalFilename());

        // 如果要保存的文件夹不存在，则创建文件夹
        File savePathDir = new File(savePath);
        if (!savePathDir.exists()) {
            savePathDir.mkdirs();
        }

        // 保存文件
        rimageFile.transferTo(new File(savePathDir, filename));

        // 设置route的rimage属性为图片相对路径
        route.setRimage("img/product/rimage/" + filename);

        System.out.println("更新后的route：");
        System.out.println(route);
    }
}
