package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Admin;
import pe.isil.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements BaseService<Admin, String> {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public Optional<Admin> findById(String id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<List<Admin>> findAll() {
        return Optional.of(adminRepository.findAll());
    }

    @Override
    public Admin saveOrUpdate(Admin admin) {
        return adminRepository.save(admin);
    }



    @Override
    public boolean deleteById(String id) {
        return findById(id)
                .map(admin -> {
                    adminRepository.delete(admin);
                    return true;

                }).orElse(false);
    }
}
