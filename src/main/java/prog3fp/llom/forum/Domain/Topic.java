package prog3fp.llom.forum.Domain;

import jdk.jfr.Timestamp;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicId;
    @Column(unique = true)
    private String subject;
    private Long userId;
    @DateTimeFormat
    private String creationDate;
    @Transient
    private List<Post> postList = new ArrayList<>();

    public Topic(){}
    public Topic(Topic topic) {
    }
}
