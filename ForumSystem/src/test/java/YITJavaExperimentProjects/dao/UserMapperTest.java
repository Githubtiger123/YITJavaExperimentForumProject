package YITJavaExperimentProjects.dao;

import com.YITJavaExperimentProjects.model.User;
import com.YITJavaExperimentProjects.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;

public class UserMapperTest {


    @Test
    public void test() {

        UserService userService = new UserService();
//        userService.registerUser(new User("i坤坤","A1Dik0@kk.com","123456"));

//        userService.loginUser(new User("i坤坤","A1Dik0@kk.com","123456"));
        userService.registerUser(new User("周蒴蒴","zhoushuo@s435.com","123456"));
    }
}
