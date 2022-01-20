package com.xxy.mapper;

import com.xxy.exception.DuplicateCodeException;
import com.xxy.pojo.Express;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ExpressMapper {
    /**
     * 用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     *
     */
    Map<String, Integer> console();


    /**
     * 用于查询所有快递
     * @param offset SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递的集合
     */
    List<Express> findByLimit(@Param("offset") int offset, @Param("pagenumber") int pageNumber);
    List<Express> findAll();

    /**
     * 根据快递单号，查询快递信息
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    Express findByNumber(String number);

    /**
     * 根据快递取件码，查询快递信息
     * @param code 取件码
     * @return 查询的快递信息，取件码不存在时返回null
     */
    Express findByCode(String code);

    /**
     * 根据用户手机号码，查询他所有的快递信息
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */
    List<Express> findByUserPhone(@Param("userphone") String userPhone);

    /**
     * 根据录入人手机号码，查询录入的所有记录
     * @param sysPhone 手机号码
     * @return 查询的快递信息列表
     */
    List<Express> findBySysPhone(@Param("sysphone") String sysPhone);

    /**
     * 快递的录入
     * @param e 要录入的快递对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    boolean insert(Express e) throws DuplicateCodeException;

    /**
     * 快递的修改
     * @param newExpress 新的快递对象（number，company,username,userPhone）
     * @return 修改的结果，true表示成功，false表示失败
     */
    boolean update(Express newExpress);

    /**
     * 更改快递的状态为1，表示取件完成
     * @param code 要修改的快递取件码
     * @return 修改的结果，true表示成功，false表示失败
     */
    boolean updateStatus(String code);

    /**
     * 根据id，删除单个快递信息
     * @param id 要删除的快递id
     * @return 删除的结果，true表示成功，false表示失败
     */
    boolean delete(int id);

    List<Express> findByUserPhoneAndStatus(@Param("userphone") String userPhone, @Param("status") int status);

    /**
     * 获得总排名
     * @param offset 分页查询：偏移位置（这里一般选择0）
     * @param pageNumber 分页查询：每页有几条数据
     * @return 返回map集合，包括nameListTotal、scoreListTotal
     */
    ArrayList<Map<Object, Object>> getTotalRank (@Param("offset") int offset, @Param("pagenumber") int pageNumber);

    ArrayList<Map<Object, Object>> getYearRank (@Param("offset") int offset, @Param("pagenumber") int pageNumber);

    ArrayList<Map<Object, Object>> getMonthRank (@Param("offset") int offset, @Param("pagenumber") int pageNumber);
}
