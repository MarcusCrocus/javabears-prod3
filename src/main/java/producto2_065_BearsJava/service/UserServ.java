package producto2_065_BearsJava.service;

import org.springframework.stereotype.Service;
import producto2_065_BearsJava.model.User;
@Service
public interface UserServ {

    public Iterable<User> getAllUsers();

    public User createUser(User user) throws Exception;
    public User getUserById(Long id) throws Exception;

    public User updateUser(User user) throws Exception;
}
