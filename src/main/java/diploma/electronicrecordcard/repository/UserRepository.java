package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u.roles from User u where u.id = :id")
    List<RoleDto> findRolesByUserId(@Param("id") Long id);

}
