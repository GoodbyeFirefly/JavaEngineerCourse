package com.xxy.mapper;

import com.xxy.pojo.Courier;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourierMapper {
    List<Courier> findAllByLimit(@Param("offset") int offset,
                                 @Param("pagenumber") int pageNumber);

    List<Courier> findAll();

    Integer getTotal();

    Boolean insert(Courier courier);

    Courier findByPhone(@Param("courierphone") String courierphone);

    Boolean update(Courier courier);

    Boolean delete(String number);

    Integer getCourierDay();

}
