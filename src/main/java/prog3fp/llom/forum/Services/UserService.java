package prog3fp.llom.forum.Services;



import prog3fp.llom.forum.Domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);

    List<User> findAllUsers();

    User save(User teacher);

    void delete(Long id);

    Optional<User> findUserByUsername(String username);
}
