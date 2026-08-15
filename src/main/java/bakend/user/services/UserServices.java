package bakend.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bakend.user.entities.User;
import bakend.user.models.UserRequest;

@Service
public interface UserServices {

    List<User> findAllUsers();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);

    Optional <User> update(UserRequest user,Long id);

}
