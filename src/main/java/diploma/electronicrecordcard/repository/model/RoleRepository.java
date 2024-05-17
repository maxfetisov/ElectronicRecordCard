package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {

    Optional<Role> findByName(String name);

}
