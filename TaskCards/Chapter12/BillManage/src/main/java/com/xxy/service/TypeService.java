package com.xxy.service;

import com.xxy.entity.BillType;
import com.xxy.mapper.TypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeService {
    @Resource
    private TypeMapper typeMapper;

    public List<BillType> selectAll() {
        return typeMapper.selectAll();
    }
}
