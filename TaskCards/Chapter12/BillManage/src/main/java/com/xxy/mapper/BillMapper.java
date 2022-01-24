package com.xxy.mapper;

import com.xxy.entity.Bill;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BillMapper extends Mapper<Bill> {
    public List<Bill> select(Bill b);
}
