package com.xxy.service;

import com.xxy.mapper.CourierMapper;
import com.xxy.pojo.Courier;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourierService {
    @Resource
    private CourierMapper courierMapper;

    public List<Courier> findall(boolean limit, int offset, int pageNumber) {
        if (limit) {
            return courierMapper.findAllByLimit(offset, pageNumber);
        } else {
            return courierMapper.findAll();
        }
    }

    public int gettotal() {
        return courierMapper.getTotal();
    }

    public Boolean insert(Courier courier) {
        return courierMapper.insert(courier);
    }

    public Courier findByPhone(String courierphone) {
        return courierMapper.findByPhone(courierphone);
    }

    public Boolean update(Courier courier) {
        return courierMapper.update(courier);
    }

    public Boolean delete(String number) {
        return courierMapper.delete(number);
    }

    public Map<String, Integer> console() {
        Map<String, Integer> map = new HashMap<>();
        map.put("courier_size", courierMapper.getTotal());
        map.put("courier_day", courierMapper.getCourierDay());
        return map;
    }
}
