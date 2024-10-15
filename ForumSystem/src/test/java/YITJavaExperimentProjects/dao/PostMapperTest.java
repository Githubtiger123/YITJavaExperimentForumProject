package YITJavaExperimentProjects.dao;

import com.YITJavaExperimentProjects.model.Post;
import com.YITJavaExperimentProjects.service.PostService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PostMapperTest {

    @Test
    public void testInsert() {
        PostService postService = new PostService();
//        postService.createPost(new Post("下包有多安全，拆包就有多安全","下包有多安全，拆包就有多安全",2,1));
//        postService.createPost(new Post("多做多错，少做少错，不做不错，不错不错","就一个字-tang.....",2,4));
//        System.out.println(postService.getPostById(1));
        postService.getAllPost(2).forEach(System.out::println);
    }
}
