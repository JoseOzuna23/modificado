package com.coop8.demojwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coop8.demojwt.Models.CategoriaSocios;



@Repository
public interface CategoriaSocioRepository extends JpaRepository<CategoriaSocios, Integer> {

    Optional<CategoriaSocios> findById(Long id_categoria_socios);   

    void deleteById(Long id_categoria_socios);

}
