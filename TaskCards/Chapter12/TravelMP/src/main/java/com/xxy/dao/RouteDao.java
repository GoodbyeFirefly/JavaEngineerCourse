package com.xxy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxy.domain.Route;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteDao extends BaseMapper<Route> {
    // 因为线路需要查询线路详细图片列表 routeImgList ，线路所属分类 category ，线路所属旅行社 seller
    // 使用内置方法也可以，但是比较复杂。
    // 这里在 xml映射文件中使用resultMap的association和collection查询
    public List<Route> find(Route conditioin);
    public Route findById(Integer id);
}
