package pe.isil.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.isil.model.User;
import pe.isil.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        List<User> users= new ArrayList<>(
                Arrays.asList(
                        new User("Jorge", "Henríquez", "jorge", passwordEncoder.encode("j123456")),
                        new User("Fabián", "Bocanegra", "fabian",passwordEncoder.encode("f123456")),
                        new User("Sebastián", "Borrajeros", "sebastian",passwordEncoder.encode("s123456"))
                )
        );

        userRepository.saveAll(users);
    }
}
