package com.coop8.demojwt.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coop8.demojwt.Models.Ciudad;


@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

	/**
	 * Busca y obtiene un {Optional<Localidades>} en base al campo descripcion
	 * 
	 * @author carlos.barrera
	 * @since 20.04.2022
	 * @param descripcion
	 * @return Optional<Localidades>
	 */
	Optional<Ciudad> findById(Long id);

	/**
	 * Busca y obtiene un {Page<Localidades>} de todos los localidades
	 * 
	 * @author carlos.barrera
	 * @since 20.04.2022
	 * @return Page<Localidades>
	 */
	Page<Ciudad> findAllByOrderByDescripcionAsc(Pageable pageable);

	/**
	 * Busca y obtiene un {Page<Localidades>} en base al campo descripcion
	 * 
	 * @author carlos.barrera
	 * @since 20.04.2022
	 * @param descripcion
	 * @return Page<Localidades>
	 */
	Page<Ciudad> findByDescripcionContainingIgnoreCaseOrderByDescripcionAsc(String descripcion,
			Pageable pageable);

	/**
	 * Elimina un registro en base al campo id
	 * 
	 * @author carlos.barrera
	 * @since 20.04.2022
	 * @param id
	 */

}