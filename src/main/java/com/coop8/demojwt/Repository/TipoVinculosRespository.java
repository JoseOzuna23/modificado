package com.coop8.demojwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coop8.demojwt.Models.TipoVinculos;


@Repository
public interface TipoVinculosRespository extends JpaRepository<TipoVinculos, Integer>  {

    Optional<TipoVinculos> findById(Long id_tipo_vinculos);   

    void deleteById(Long id_tipo_vinculos);


    
} 