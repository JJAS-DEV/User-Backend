package bakend.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bakend.user.entities.User;
@Service
public interface UserServices {

    List<User> findAllUsers();

     Optional<User> findById(Long id);
     User save(User user);
     void deleteById(Long id);

}
