package com.xxy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xxy.dao.RouteImgDao;
import com.xxy.domain.RouteImg;
import com.xxy.service.RouteImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RouteImgServiceImpl implements RouteImgService {
    @Autowired
    private RouteImgDao routeImgDao;

    @Override
    @Transactional
    public void saveImg(Integer rid, List<RouteImg> routeImgs) {
        routeImgDao.delete(Wrappers.<RouteImg>query().eq("rid", rid));
        for (RouteImg ri : routeImgs) {
            routeImgDao.insert(ri);
        }
    }
}
