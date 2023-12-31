package producto2_065_BearsJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import producto2_065_BearsJava.model.User;
import producto2_065_BearsJava.repository.UserRepo;

import java.util.Optional;

@Service
public class UserServImpl implements UserServ {
    @Autowired
    UserRepo repository;

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    private boolean checkUsernameAvailable(User user) throws Exception {
        Optional<User> userFound = repository.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new Exception("Username no disponible");
        }
        return true;
    }

    private boolean checkPasswordValid(User user) throws Exception {
        if ( !user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Password y Confirm Password no son iguales");
        }
        return true;
    }


    @Override
    public User createUser(User user) throws Exception {
        if (checkUsernameAvailable(user) && checkPasswordValid(user)) {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            user = repository.save(user);
        }

        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El usuario no existe."));
    }

    @Override
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);
        return repository.save(toUser);
    }

    /**
     * Map everythin but the password.
     * @param from
     * @param to
     */
    protected void mapUser(User from,User to) {
        to.setUsername(from.getUsername());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        repository.delete(user);
    }
}
