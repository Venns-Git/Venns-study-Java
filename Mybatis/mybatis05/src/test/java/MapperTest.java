import com.venns.dao.TeacherMapper;
import com.venns.pojo.Teacher;
import com.venns.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        System.out.println(teacher);
        sqlSession.close();
    }
    @Test
    public void test2(){
        Teacher teacher = MybatisUtils.getSqlSession().getMapper(TeacherMapper.class).getTeacher2(1);
        System.out.println(teacher);
    }
}
