package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    @Query("select r from User u join u.roles r where u.id = :id")
    List<Role> findRolesByUserId(@Param("id") Long id);

    boolean existsByLogin(String login);

}
