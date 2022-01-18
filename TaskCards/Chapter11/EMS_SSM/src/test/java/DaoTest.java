import com.xxy.mapper.AdminMapper;
import com.xxy.mapper.CourierMapper;
import com.xxy.pojo.Courier;
import com.xxy.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DaoTest {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private CourierMapper courierMapper;



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
