package pe.isil.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.model.Tip;

@Repository
public interface TipRepository extends JpaRepository<Tip, String> {
}
