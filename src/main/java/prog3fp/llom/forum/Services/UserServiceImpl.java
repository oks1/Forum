package prog3fp.llom.forum.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prog3fp.llom.forum.Domain.User;
import prog3fp.llom.forum.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setRoles("ROLE_USER");
        user.setActive(true);
        //check if user exists
        if(userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            //get password from database if user not null
            Optional<User> passCheck = userRepository.findUserByUsername(user.getUsername());
            String password = passCheck.map(User::getPassword).orElse(null);
            //check if form password = database password, if not encode.
            if (!user.getPassword().equals(password)) {
                user.setPassword(encoder.encode(user.getPassword()));
            }
        } else {
            //if user is not in database, encode password
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
