package user;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.perkins.oes.AppContext;
import com.perkins.oes.Constants;
import com.perkins.oes.controller.UserController;
import com.perkins.oes.entity.User;
import com.perkins.oes.util.PathUtil;
import com.perkins.oes.util.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:springMVC-servlet.xml" })
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void setAppContext() throws Exception {
        AppContext.setContextPath("/OESspring");
        AppContext appContext = AppContext.getAppContext();
        appContext.addObjects(Constants.APP_CONTEXT_SESSION, new MockHttpSession());
    }

    @After
    public void clearAppContext() throws Exception {
        AppContext appContext = AppContext.getAppContext();
        appContext.clear();
    }

    @Test
    public void testUserControllerLogin() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        User user = new User();
        user.setUserName("perkins");
        user.setPassword("1");
        ModelAndView mod = userController.login(user);
        RedirectView redirectView = (RedirectView) mod.getView();
        Assert.assertEquals(PathUtil.getPath() + Constants.PATH_QUESTION_SHOW_lIST, redirectView.getUrl());
        Assert.assertNotNull(SessionUtil.getSession(Constants.USER));
    }
}
