package sta.cs5031p3.mealtimetinder.backend.repository;

import org.springframework.data.repository.CrudRepository;
import sta.cs5031p3.mealtimetinder.backend.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByStatusAndUsernameAndPassword(User.Status status, String username, String password);
}
