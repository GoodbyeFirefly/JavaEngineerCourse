package com.xxy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("tab_route")
public class Route implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer rid;//线路id，必输
    private String rname;//线路名称，必输
    private Double price;//价格，必输
    private String routeIntroduce;//线路介绍
    private String rflag; //是否上架，必输，0代表没有上架，1代表是上架
    private String rdate; //上架时间
    private String isThemeTour;//是否主题旅游，必输，0代表不是，1代表是
    private Integer count;//收藏数量

    private Integer cid;//所属分类，必输
    private String rimage;//缩略图
    private Integer sid;//所属商家

    private String sourceId;//抓取数据的来源id

    @TableField(exist = false)
    private Category category;//所属分类 使用resultmap的assocation处理

    @TableField(exist = false)
    private Seller seller;//所属商家 使用resultmap的assocation处理

    @TableField(exist = false)
    private List<RouteImg> routeImgList;//商品详情图片列表,关联属性，mybatis plus不能查，需要配置resultmap使用resultmap的collection处理

    @Override
    public String toString() {
        return "Route{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", price=" + price +
                ", routeIntroduce='" + routeIntroduce + '\'' +
                ", rflag='" + rflag + '\'' +
                ", rdate='" + rdate + '\'' +
                ", isThemeTour='" + isThemeTour + '\'' +
                ", count=" + count +
                ", cid=" + cid +
                ", rimage='" + rimage + '\'' +
                ", sid=" + sid +
                ", sourceId='" + sourceId + '\'' +
                ", category=" + category +
                ", seller=" + seller +
                ", routeImgList=" + routeImgList +
                '}';
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getRflag() {
        return rflag;
    }

    public void setRflag(String rflag) {
        this.rflag = rflag;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

}
