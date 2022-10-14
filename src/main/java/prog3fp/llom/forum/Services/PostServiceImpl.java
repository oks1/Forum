package prog3fp.llom.forum.Services;

import org.springframework.stereotype.Service;
import prog3fp.llom.forum.Domain.Post;
import prog3fp.llom.forum.Repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findPostByPostId(Long postId){return postRepository.findById(postId);}

    @Override
    public List<Post> findTopicPosts(Long topicId) {
        return postRepository.findPostsByTopicId(topicId);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }


    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}

