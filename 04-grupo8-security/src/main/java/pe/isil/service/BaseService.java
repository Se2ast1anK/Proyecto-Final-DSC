package pe.isil.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <C, K> {


    //coger 1
    public Optional<C> findById(K  k );
    //tener todos
    Optional< List<C>> findAll();
    //eliminar todos
    public C saveOrUpdate(C c);
    //eliminar
    public boolean deleteById(K k);



}
