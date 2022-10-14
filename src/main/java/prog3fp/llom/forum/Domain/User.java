package prog3fp.llom.forum.Domain;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotEmpty
    @Size(min=8, max=20, message = "Username : 8 to 20 characters")
    @Column(unique = true)
    private String username;

    private String password;
    @Transient
    private String password2;
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;
    @URL
    private String photo;
    private boolean active;
    private String roles;

    @NotEmpty
    private String fullname;

    @Size(min=10, max=10, message = "Cellphone : 10 digits")
    private String cellphone;


    @OneToMany(mappedBy = "user")
    private List<Post> post = new ArrayList<>();
}
