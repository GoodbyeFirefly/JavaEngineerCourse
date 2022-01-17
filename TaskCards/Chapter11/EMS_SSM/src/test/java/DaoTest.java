import com.xxy.mapper.AdminMapper;
import com.xxy.service.AdminService;
import com.xxy.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DaoTest {
    @Resource
    private AdminMapper adminMapper;

    @Test
    public void AdminMapperTest() {

        String login = adminMapper.login("admin", "1234");
        if (login == null) System.out.println("false");
        else System.out.println("true");
    }

    @Test
    public void AdminLoginUpdateTest() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.updateLoginTime("admin", new Date(), "127.0.0.1");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void AdminLoginUpdateServiceTest() {
        AdminService.updateLoginTime("admin", new Date(), "127.0.0.2");

    }
}
