package com.xxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxy.dao.RouteDao;
import com.xxy.domain.Route;
import com.xxy.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteDao routeDao;

    @Override
    public PageInfo<Route> findPage(Route conditioin, int pageNum, int pageSize) {
        // 查询条件（input组件的value）为空时，默认的为''而不是null
        // mp内置的条件查询会将''作为条件之一，从而导致查询不出结果
        if (conditioin != null) {
            if ("".equals(conditioin.getRname())) conditioin.setRname(null);
            if ("".equals(conditioin.getRouteIntroduce())) conditioin.setRouteIntroduce(null);
            if (conditioin.getCategory() != null && "".equals(conditioin.getCategory().getCname())) conditioin.setCategory(null);
            if (conditioin.getSeller() != null && "".equals(conditioin.getSeller().getSname())) conditioin.setSeller(null);
        }
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            // 需要将查询条件作为参数传入
            routeDao.find(conditioin);
        });
    }

    @Override
    public int add(Route route) {
        return routeDao.insert(route);
    }

    @Override
    public Route findById(Integer id) {
        return routeDao.findById(id);
    }

    @Override
    public int update(Route route) {
        return routeDao.updateById(route);
    }

    @Override
    public int delete(Integer id) {
        return routeDao.deleteById(id);
    }
}
