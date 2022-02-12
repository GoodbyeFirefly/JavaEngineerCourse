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
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
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