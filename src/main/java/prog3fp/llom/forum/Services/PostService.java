package prog3fp.llom.forum.Services;

import prog3fp.llom.forum.Domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findPostByPostId(Long postId);

    List<Post> findTopicPosts(Long topicId);

    Post save(Post teacher);

    void delete(Long id);


}