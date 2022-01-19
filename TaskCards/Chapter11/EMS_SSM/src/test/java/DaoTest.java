import com.xxy.exception.DuplicateCodeException;
import com.xxy.mapper.AdminMapper;
import com.xxy.mapper.CourierMapper;
import com.xxy.mapper.ExpressMapper;
import com.xxy.mapper.UserMapper;
import com.xxy.pojo.Courier;
import com.xxy.pojo.Express;
import com.xxy.pojo.User;
import com.xxy.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DaoTest {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private CourierMapper courierMapper;
    @Resource
    private ExpressMapper expressMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * UserMapperTest
     */
    @Test
    public void findAllUser() {
        List<User> users = userMapper.findAll();
        for (User u : users) System.out.println(u);
    }
    @Test
    public void findByLimitUser() {
        List<User> users = userMapper.findByLimit(0, 5);
        for (User u : users) System.out.println(u);
    }
    @Test
    public void getTotalUser() {
        int total = userMapper.getTotal();
        System.out.println(total);
    }
    @Test
    public void insertUser() {
        User user = new User("申鹤", "18856361234", "411328199009091234", "123");
        Boolean total = userMapper.insert(user);
    }
    @Test
    public void findByPhoneUser() {
        User user = userMapper.findByPhone("18856361234");
        System.out.println(user);
    }
    @Test
    public void updateUser() {
        User user = new User("申鹤-小姨", "18856361234", "411328199009091234", "123");
        user.setNumber(19);
        userMapper.update(user);
    }
    @Test
    public void deleteUser() {
        userMapper.delete("19");
    }
    @Test
    public void consoleUser() {
        Map<String, Integer> console = userMapper.console();
        System.out.println(console.keySet());
    }

    /**
     * ExpressMapperTest
     */
    @Test
    public void consloe() {
//        Integer data1Size = expressMapper.getData1Size();
//        Integer data1Day = expressMapper.getData1Day();
//        Integer data2Size = expressMapper.getData2Size();
//        Integer data2Day = expressMapper.getData2Day();
//        System.out.println(data1Day + " " + data1Size + " " + data2Size + " " + data2Day);
        List<Integer> console = expressMapper.console();
    }
    @Test
    public void findAllExpress(){
        List<Express> expressList = expressMapper.findAll();
        for (Express e : expressList) System.out.println(e);
    }
    @Test
    public void findByLimitExpress() {
        List<Express> expressList = expressMapper.findByLimit(0, 5);
        for (Express e : expressList) System.out.println(e);
    }
    @Test
    public void findByNumberExpress(){
        Express express = expressMapper.findByNumber("122");
        System.out.println(express);
    }
    @Test
    public void findByCodeExpress() {
        Express express = expressMapper.findByCode("133006");
        System.out.println(express);
    }
    @Test
    public void findByUserphoneExpress(){
        List<Express> expressList = expressMapper.findByUserPhone("16662626262");
        for (Express e : expressList) System.out.println(e);
    }
    @Test
    public void findBySysphoneExpress(){
        List<Express> expressList = expressMapper.findBySysPhone("18888888888");
        for (Express e : expressList) System.out.println(e);
    }
    @Test
    public void insertExpress(){
        Express e = new Express("124", "申鹤", "1885631234", "邮政", "133011", "16666666666");
        e.setStatus(1);
        boolean insert = false;
        try {
            insert = expressMapper.insert(e);
        } catch (DuplicateCodeException e1) {
            e1.printStackTrace();
        }
        if (insert) System.out.println(true);
        else System.out.println(false);
    }
    @Test
    public void updateExpress(){
        Express e = new Express("124", "申鹤", "1885634321", "顺丰", "133011", "16666666666");
        e.setId(34);
        boolean update = expressMapper.update(e);
        if (update) System.out.println(true);
        else System.out.println(false);
    }
    @Test
    public void updateStatusExpress(){
        boolean update = expressMapper.updateStatus("133011");
        if (update) System.out.println(true);
        else System.out.println(false);
    }
    @Test
    public void findByUserphoneAndStatusExpress(){
        List<Express> expressList = expressMapper.findByUserPhoneAndStatus("16662626262", 0);
        for (Express e : expressList) System.out.println(e);
    }
    @Test
    public void getTotalRankExpress() {
        ArrayList<Map<Object, Object>> totalRankName = expressMapper.getTotalRank(0, 3);
        for (Map<Object, Object> map : totalRankName) System.out.println(map);
    }
    @Test
    public void getYearRankExpress() {
        ArrayList<Map<Object, Object>> totalRankName = expressMapper.getYearRank(0, 3);
        for (Map<Object, Object> map : totalRankName) System.out.println(map);
    }
    @Test
    public void getMonthRankExpress() {
        ArrayList<Map<Object, Object>> totalRankName = expressMapper.getMonthRank(0, 3);
        for (Map<Object, Object> map : totalRankName) System.out.println(map);
    }

    /**
     * CourierMapperTest
     */
    @Test
    public void findAll() {
        List<Courier> list = courierMapper.findAll();
        for (Courier c : list) System.out.println(c);
    }
    @Test
    public void findAllByLimit() {
        List<Courier> list = courierMapper.findAllByLimit(1, 2);
        for (Courier c : list) System.out.println(c);
    }
    @Test
    public void getTotal() {
        int total = courierMapper.getTotal();
        System.out.println(total);
    }
    @Test
    public void insert() {
        Courier courier = new Courier("申鹤", "18856601234", "411325199806061221", "1234");
        Boolean insert = courierMapper.insert(courier);
        if (insert) System.out.println("true");
        else System.out.println("false");
    }
    @Test
    public void findByPhone() {
        Courier courier = courierMapper.findByPhone("18856601234");
        System.out.println(courier);
    }
    @Test
    public void update() {
        Courier courier = new Courier(13, "申鹤-小姨", "18856601234", "411325199806061221", "123");
        courierMapper.update(courier);
    }
    @Test
    public void delete() {
        courierMapper.delete("13");
    }



    /**
     * AdminMapperTest
     */
    @Test
    public void AdminMapperTest() {

        String login = adminMapper.login("admin", "1234");
        if (login == null) System.out.println("false");
        else System.out.println("true");
    }

    @Test
    public void AdminLoginUpdateTest() {
        adminMapper.updateLoginTime("admin", new Date(), "127.0.0.3");
    }
}
