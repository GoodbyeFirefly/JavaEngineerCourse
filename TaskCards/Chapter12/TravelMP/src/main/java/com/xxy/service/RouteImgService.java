package com.xxy.service;

import com.xxy.domain.RouteImg;

import java.util.List;

public interface RouteImgService {
    /**
     * 保存图片
     * @param rid 根据线路id删除原先保留的图片
     * @param routeImgs 将新添加的图片插入到数据库中
     */
    public void saveImg(Integer rid, List<RouteImg> routeImgs);
}
