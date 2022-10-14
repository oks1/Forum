package prog3fp.llom.forum.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog3fp.llom.forum.Domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByTopicId(Long topicId);

//    void deletePostByPostId(Long postId);

    @Override
    Optional<Post> findById(Long postId);


}
