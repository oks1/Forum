package prog3fp.llom.forum.Domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    @Size(min=15, max=500, message = "A post can contain 15-500 characters.")
    private String text;
    @DateTimeFormat
    private String creationDate;
//    private Long userId;
    private Long topicId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
