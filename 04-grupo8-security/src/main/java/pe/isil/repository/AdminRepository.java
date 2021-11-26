package pe.isil.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository <Admin, String> {
}
