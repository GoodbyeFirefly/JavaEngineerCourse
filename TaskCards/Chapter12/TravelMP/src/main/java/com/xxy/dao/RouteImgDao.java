package com.xxy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxy.domain.RouteImg;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteImgDao extends BaseMapper<RouteImg> {

    @Select("select * from tab_route_img where rid = #{rid}")
    public List<RouteImg> findByRid(Integer rid);

}
