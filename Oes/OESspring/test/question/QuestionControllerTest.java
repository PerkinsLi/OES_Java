package question;

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
import com.perkins.oes.controller.QuestionController;
import com.perkins.oes.entity.Question;
import com.perkins.oes.util.PathUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:springMVC-servlet.xml" })
public class QuestionControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

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
    public void showListTest() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");

        String currentPage = null;
        String pagesize = null;
        String sort = null;
        String searchText = null;

        ModelAndView mod = questionController.showList(currentPage, pagesize, sort, searchText);

        String viewName = mod.getViewName();
        System.out.println(mod.getModel().get("questions"));
        System.out.println(mod.getModel().get("page"));
        System.out.println(mod.getModel().get("searchText"));

        Assert.assertNotNull(mod.getModel().get("questions"));
        Assert.assertNotNull(mod.getModel().get("page"));
        // Assert.assertNotNull(mod.getModel().get("searchText"));
        Assert.assertEquals(Constants.PATH_SHOW_CONTENTADMIN_MAIN, viewName);
    }

    @Test
    public void questionInformationTest() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");
        String qid = "1";
        ModelAndView mod = questionController.questionInformation(qid);
        String viewName = mod.getViewName();
        System.out.println(mod.getModel().get("question"));
        Assert.assertEquals(Constants.PATH_SHOW_INFORMATION_QUESTION, viewName);

    }

    @Test
    public void toSaveTest() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");
        ModelAndView mod = questionController.toSave();
        String viewName = mod.getViewName();
        System.out.println(mod.getModel().get("questionID"));
        Assert.assertEquals(Constants.PATH_SHOW_CREATE_QUESTION, viewName);
    }

    @Test
    public void saveTest() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");
        Question question = new Question();
        question.setTitle("Junit test");
        question.setAnswerA("test1");
        question.setAnswerB("test2");
        question.setAnswerC("test3");
        question.setAnswerD("test4");
        question.setRightAnswer("a");
        ModelAndView mod = questionController.save(question);
        RedirectView redirectView = (RedirectView) mod.getView();
        Assert.assertEquals(PathUtil.getPath() + Constants.PATH_QUESTION_SHOW_lIST, redirectView.getUrl());
    }

    @Test
    public void deleteTest() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");
        String arrays = "1,2,3";
        // ModelAndView mod = questionController.delete(arrays);
        // RedirectView redirectView = (RedirectView) mod.getView();
        // Assert.assertEquals(PathUtil.getPath() +
        // Constants.PATH_QUESTION_SHOW_lIST, redirectView.getUrl());
    }

    @Test
    public void toEditTest() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");
        String qid = "1";
        ModelAndView mod = questionController.toEdit(qid);
        System.out.println(mod.getModel().get("question"));
        Assert.assertEquals(Constants.PATH_SHOW_EDIT_QUESTION, mod.getViewName());
    }

    @Test
    public void editTest() {
        QuestionController questionController = (QuestionController) this.applicationContext
                .getBean("questionController");
        Question question = new Question();
        question.setTitle("Junitupdate");
        question.setAnswerA("A");
        question.setAnswerB("B");
        question.setAnswerC("C");
        question.setAnswerD("D");
        question.setRightAnswer("a");
        ModelAndView mod = questionController.edit(question);
        RedirectView redirectView = (RedirectView) mod.getView();
        Assert.assertEquals(PathUtil.getPath() + Constants.PATH_QUESTION_SHOW_lIST, redirectView.getUrl());
    }

}
