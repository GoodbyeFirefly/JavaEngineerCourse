import com.xxy.mapper.AdminMapper;
import com.xxy.service.AdminService;
import com.xxy.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;

public class DaoTest {

    @Test
    public void AdminMapperTest() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        String login = adminMapper.login("admin", "1234");
        if (login == null) System.out.println("false");
        else System.out.println("true");
        sqlSession.close();
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
