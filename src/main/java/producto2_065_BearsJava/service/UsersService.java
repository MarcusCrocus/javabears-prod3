package producto2_065_BearsJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import producto2_065_BearsJava.repository.UsersRepo;

import producto2_065_BearsJava.model.Users;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepo usersRepo;

    @Autowired
    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepo.findById(id);
    }

    public void guardarUser(Users user) {
        this.usersRepo.save(user);
    }

    public void eliminarUser(Long id) {
        this.usersRepo.deleteById(id);
    }
}
