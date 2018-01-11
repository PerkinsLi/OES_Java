package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.perkins.oes.exception.ParameterException;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:springMVC-servlet.xml" })
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Test
    public void login() {
        UserService userService = (UserService) this.applicationContext.getBean("userService");
        String userName = "perkins";
        String password = "1";
        try {
            userService.login(userName, password);
        } catch (ParameterException | ServiceException e) {
            e.printStackTrace();
        }
    }
}
