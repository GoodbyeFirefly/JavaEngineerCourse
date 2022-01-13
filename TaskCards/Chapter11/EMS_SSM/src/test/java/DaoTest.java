import com.xxy.mapper.AdminMapper;
import com.xxy.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class DaoTest {
    private SqlSession sqlSession = MybatisUtil.getSqlSession();

    @Test
    public void AdminMapperTest() {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        String login = adminMapper.login("admin", "1234");
        if (login == null) System.out.println("false");
        else System.out.println("true");

    }
}
