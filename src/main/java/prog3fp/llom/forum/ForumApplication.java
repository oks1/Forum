package prog3fp.llom.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import prog3fp.llom.forum.Repositories.UserRepository;


@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
