import com.venns.config.VennsConfig;
import com.venns.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        //如果完全使用配置类方式，就只能通过 AnnotationConfigApplicationContext 获取容器，通过配置类的class对象加载
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(VennsConfig.class);
        User getUser = (User) context.getBean("User");
        System.out.println(getUser.getName());
    }
}
