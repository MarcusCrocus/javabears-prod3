package producto2_065_BearsJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import producto2_065_BearsJava.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

}
