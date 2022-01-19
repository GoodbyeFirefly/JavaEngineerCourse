package com.xxy.service;

import com.xxy.pojo.Express;
import com.xxy.exception.DuplicateCodeException;
import com.xxy.mapper.ExpressMapper;
import com.xxy.util.RandomUtil;
import com.xxy.util.SMSUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressService {
    @Resource
    private ExpressMapper expressMapper;

    /**
     * 用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     *
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    public List<Map<String, Integer>> console() {
        List<Map<String, Integer>> data = new ArrayList<>();
        Map<String, Integer> data1 = new HashMap<>(), data2 = new HashMap<>();
        List<Integer> console = expressMapper.console();
        data1.put("data1_size", console.get(0));
        data1.put("data1_day", console.get(1));
        data2.put("data2_size", console.get(2));
        data2.put("data2_day", console.get(3));
        data.add(data1);
        data.add(data2);
        return data;
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记，true表示分页。false表示查询所有快递
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递的集合
     */
    public List<Express> findAll(boolean limit, int offset, int pageNumber) {
        if (limit) {
            return expressMapper.findByLimit(offset, pageNumber);
        } else {
            return expressMapper.findAll();
        }
    }

    /**
     * 根据快递单号，查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    public Express findByNumber(String number) {
        return expressMapper.findByNumber(number);
    }

    /**
     * 根据快递取件码，查询快递信息
     *
     * @param code 取件码
     * @return 查询的快递信息，取件码不存在时返回null
     */
    public Express findByCode(String code) {
        return expressMapper.findByCode(code);
    }

    /**
     * 根据用户手机号码，查询他所有的快递信息
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */
    public List<Express> findByUserPhone(String userPhone) {
        return expressMapper.findByUserPhone((userPhone));
    }

    /**
     * 根据录入人手机号码，查询录入的所有记录
     *
     * @param sysPhone 手机号码
     * @return 查询的快递信息列表
     */
    public List<Express> findBySysPhone(String sysPhone) {
        return expressMapper.findBySysPhone(sysPhone);
    }

    /**
     * 快递的录入
     *
     * @param e 要录入的快递对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    public boolean insert(Express e) {
        e.setCode(RandomUtil.getCode()+"");
        try {
            boolean insert = expressMapper.insert(e);
            boolean send = false;
            if (insert) {
                send = SMSUtil.send(e.getUserphone(), e.getCode());
            }
            return send;
        } catch (DuplicateCodeException e1) {
            // 捕获到取件码重复后 递归调用插入
            return insert(e);
        }
    }

    /**
     * 快递的修改
     *
     * @param newExpress 新的快递对象（number，company,username,userPhone）
     * @return 修改的结果，true表示成功，false表示失败
     */
    public boolean update(Express newExpress) {
        // 个人理解：当有修改收件人号码的需求时，由于涉及到重发短信的业务，需要重新执行插入（插入时，会向用户发送短信）
        if (newExpress.getUserphone() != null) {
            expressMapper.delete(newExpress.getId());
            return insert(newExpress);
        } else {
            // 这里的逻辑感觉不是很清晰，注释上修改的是userPhone，后面SQL语句中却替换为status，后面有问题再来修改吧
            boolean update = expressMapper.update(newExpress);
            Express e = expressMapper.findByNumber(newExpress.getNumber());
            if (newExpress.getStatus() == 1) {
                updateStatus(e.getCode());
            }
            return update;
        }
    }

    /**
     * 更改快递的状态为1，表示取件完成
     *
     * @param code 要修改的快递取件码
     * @return 修改的结果，true表示成功，false表示失败
     */
    public boolean updateStatus(String code) {
        return expressMapper.updateStatus(code);
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 删除的结果，true表示成功，false表示失败
     */
    public boolean delete(int id) {
        return expressMapper.delete(id);
    }

    public List<Express> findByUserPhoneAndStatus(String userPhone, int status) {
        return expressMapper.findByUserPhoneAndStatus(userPhone, status);
    }

    public Map<String, ArrayList<String>> getTotalRankData(int offset, int pageNum) {
        ArrayList<Map<Object, Object>> totalRank = expressMapper.getTotalRank(offset, pageNum);
        Map<String, ArrayList<String>> data = new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>(), scoreList = new ArrayList<>();
        for (Map<Object, Object> map : totalRank) {
            nameList.add((String)map.get("USERNAME"));
            scoreList.add((String)map.get("score"));
        }
        data.put("nameListTotal", nameList);
        data.put("scoreListTotal", scoreList);
        return data;
    }

    public Map<String, ArrayList<String>> getYearRankData(int offset, int pageNum) {
        ArrayList<Map<Object, Object>> yearRank = expressMapper.getYearRank(offset, pageNum);
        Map<String, ArrayList<String>> data = new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>(), scoreList = new ArrayList<>();
        for (Map<Object, Object> map : yearRank) {
            nameList.add((String)map.get("USERNAME"));
            scoreList.add((String)map.get("score"));
        }
        data.put("nameListYear", nameList);
        data.put("scoreListYear", scoreList);
        return data;
    }

    public Map<String, ArrayList<String>> getMonthRankData(int offset, int pageNum) {
        ArrayList<Map<Object, Object>> monthRank = expressMapper.getMonthRank(offset, pageNum);
        Map<String, ArrayList<String>> data = new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>(), scoreList = new ArrayList<>();
        for (Map<Object, Object> map : monthRank) {
            nameList.add((String)map.get("USERNAME"));
            scoreList.add((String)map.get("score"));
        }
        data.put("nameListMonth", nameList);
        data.put("scoreListMonth", scoreList);
        return data;
    }
}
