package YITJavaExperimentProjects.dao;


import com.YITJavaExperimentProjects.dao.CommentMapper;
import com.YITJavaExperimentProjects.model.Comment;
import com.YITJavaExperimentProjects.service.CommentService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CommentMapperTest {

    @Test
    public void test() {
        CommentService commentService = new CommentService();
//        commentService.insertComment(new Comment("啊对对对", 3, 2));
        List<Comment> comments = commentService.viewComments(2);
        comments.forEach(System.out::println);
    }
}
