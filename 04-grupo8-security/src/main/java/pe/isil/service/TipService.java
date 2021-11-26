package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Tip;
import pe.isil.repository.TipRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TipService implements BaseService<Tip, String >{

    private final TipRepository tipRepository;

    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }


    @Override
    public Optional<Tip> findById(String id) {
        return tipRepository.findById(id);
    }

    @Override
    public Optional<List<Tip>> findAll() {
        return Optional.of(tipRepository.findAll());
    }

    @Override
    public Tip saveOrUpdate(Tip tip) {
        return tipRepository.save(tip);
    }


    @Override
    public boolean deleteById(String id) {

        return findById(id).map(
                tip -> {
                    tipRepository.delete(tip);
                    return true;
                }
        ).orElse(false);

    }
}
