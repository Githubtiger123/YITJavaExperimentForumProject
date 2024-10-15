package YITJavaExperimentProjects.dao;

import com.YITJavaExperimentProjects.model.Post;
import com.YITJavaExperimentProjects.model.Topic;
import com.YITJavaExperimentProjects.service.TopicService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TopicMapperTest {

    @Test
    public void test() {
        TopicService topicService = new TopicService();
//        topicService.createTopic(new Topic("瓦洛兰特","一款拳头公司开发的fps游戏"));
//        topicService.createTopic(new Topic("智博","升本就一次，当然选直博"));
//        topicService.createTopic(new Topic("雷军","卡里冰冷的40亿"));
        topicService.createTopic(new Topic("经验分享","走前人走过的路"));
//        List<Topic> topics = topicService.getTopics();
//        topics.forEach(System.out::println);
    }
}
