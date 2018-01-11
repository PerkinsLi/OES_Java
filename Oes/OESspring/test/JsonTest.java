import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.perkins.oes.entity.User;

public class JsonTest {

    @Test
    public void test1() throws JsonGenerationException, JsonMappingException, IOException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "walker");
        map.put("age", 111);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(map);
        System.out.println(jsonStr);
        System.out.println("-------------------");
        List<User> users = new ArrayList<User>();
        User user1 = new User();
        user1.setId(1);
        user1.setUserName("wwww");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setUserName("xxxx");
        users.add(user1);

        String jsonStr2 = mapper.writeValueAsString(users);
        System.out.println(jsonStr2);

    }
}
