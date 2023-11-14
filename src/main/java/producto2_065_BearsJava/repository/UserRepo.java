package producto2_065_BearsJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import producto2_065_BearsJava.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository <User, Long>{
    public Optional<User> findByUsername(String username);
}
