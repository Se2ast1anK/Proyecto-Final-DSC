package pe.isil.service;


import org.springframework.stereotype.Service;
import pe.isil.model.User;
import pe.isil.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BaseService<User, Long> {

    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;

                }).orElse(false);
    }
}
