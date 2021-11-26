package pe.isil.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.isil.model.Admin;
import pe.isil.repository.AdminRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminLoader implements CommandLineRunner {

    private final AdminRepository adminRepository;

    public AdminLoader(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Admin> administradores = new ArrayList<>();
        administradores.add(new Admin(null,"Ninguno","Ninguno","Ninguno",01,"ninguno@adm.com","X",null,null));
        administradores.add(new Admin(null,"Jorge","Henriquez","Ayala",20,"jor@adm.com","M",null,null));
        administradores.add(new Admin(null,"Michael","Fermandez","Huaman",22,"mic@adm.com","M",null,null));
        administradores.add(new Admin(null,"Maria","Perez","Fetrini",19,"mar@adm.com","F",null,null));

        adminRepository.saveAll(administradores);
    }
}
