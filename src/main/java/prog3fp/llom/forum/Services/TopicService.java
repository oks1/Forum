package prog3fp.llom.forum.Services;

import prog3fp.llom.forum.Domain.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    Topic findTopicByTopicId(Long id);

    List<Topic> findAllTopics();

    Topic save(Topic teacher);

    void delete(Long id);

    Topic findTopByOrderByTopicIdDesc();
}