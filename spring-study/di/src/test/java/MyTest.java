import com.venns.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
        /*
        Student{
            name='venns',
            address=Address{address='成都'},
            books=[三国演义, 水浒传, 红楼梦, 西游记],
            hobbys=[看电影, 敲代码, 听歌],
            card={
                身份证=12345678,
                银行卡=11111111,
                学生证=12332112},
            games=[LOL, COC, PUBG],
            wife='null',
            info={学号=20200101, 性别=男}}
         */
    }
}
