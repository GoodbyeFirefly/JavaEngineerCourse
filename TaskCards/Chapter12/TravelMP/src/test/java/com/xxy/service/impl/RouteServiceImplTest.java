package com.xxy.service.impl;

import com.github.pagehelper.PageInfo;
import com.xxy.domain.Route;
import com.xxy.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {
    @Autowired
    private RouteService routeService;

    @Test
    public void findPage() {
        Route condition = new Route();
        condition.setRname("北京");
        PageInfo<Route> page = routeService.findPage(condition, 1, 10);
        page.getList().forEach((r) -> {
            System.out.println(r.getRid() + "\t" + r.getRname() + "\t" +
                    r.getCategory().getCname() + "\t" + r.getSeller().getSname() + "\t" +
                    r.getRouteImgList().size());
        });
    }

    @Test
    public void findById() {
        Route r = routeService.findById(545);
        System.out.println(r);
    }
}