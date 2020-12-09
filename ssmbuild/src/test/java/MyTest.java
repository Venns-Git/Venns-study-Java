import com.venns.pojo.Books;
import com.venns.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = con.getBean("BookServiceImpl", BookService.class);
        for (Books books : bookServiceImpl.queryAllBook()) {
            System.out.println(books);
        }
    }
}
