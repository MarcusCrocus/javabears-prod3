package producto2_065_BearsJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import producto2_065_BearsJava.model.Role;
@Repository
public interface RoleRepo extends CrudRepository<Role,Long> {
}
