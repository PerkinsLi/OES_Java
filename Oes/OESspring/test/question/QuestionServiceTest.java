package question;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.perkins.oes.entity.Question;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.QuestionService;
import com.perkins.oes.util.PageUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:springMVC-servlet.xml" })
public class QuestionServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Test
    public void getQuestionID() {
        QuestionService questionService = (QuestionService) this.applicationContext.getBean("questionService");
        String questionID = questionService.getQuestionID();
        System.out.println(questionID);
    }

    @Test
    public void insert() {
        QuestionService questionService = (QuestionService) this.applicationContext.getBean("questionService");
        Question question = new Question();
        question.setTitle("insert test");
        question.setAnswerA("question_a");
        question.setAnswerB("question_b");
        question.setAnswerC("question_c");
        question.setAnswerD("question_d");
        questionService.insert(question);
    }

    @Test
    public void update() {
        QuestionService questionService = (QuestionService) this.applicationContext.getBean("questionService");
        Question question = new Question();
        question.setTitle("insert test");
        question.setAnswerA("question_a");
        question.setAnswerB("question_b");
        question.setAnswerC("question_c");
        question.setAnswerD("question_d");
        questionService.update(question);
    }

    @Test
    public void findList() {
        QuestionService questionService = (QuestionService) this.applicationContext.getBean("questionService");
        PageUtil pageUtil = new PageUtil();
        List<Question> questions = questionService.findList(null, null, null, null, pageUtil);
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    @Test
    public void deleteQuestion() {
        QuestionService questionService = (QuestionService) this.applicationContext.getBean("questionService");
        String arrays = "1,2";
        try {
            questionService.deleteQuestion(arrays);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        QuestionService questionService = (QuestionService) this.applicationContext.getBean("questionService");
        String qid = "1";
        try {
            Question question = questionService.findById(qid);
            System.out.println(question);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
