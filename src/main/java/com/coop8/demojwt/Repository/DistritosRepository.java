package com.coop8.demojwt.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coop8.demojwt.Models.Distrito;


@Repository
public interface DistritosRepository extends JpaRepository<Distrito, Long> {

	/**
	 * Busca y obtiene un {Optional<Distritos>} en base al campo descripcion
	 * 
	 * @author brenda.maiz
	 * @since 28.07.2022
	 * @param descripcion
	 * @return Optional<Distritos>
	 */
	Optional<Distrito> findById(Long id);

	/**
	 * Busca y obtiene un {Page<Distritos>} de todos los distritos
	 * 
	 * @author brenda.maiz
	 * @since 28.07.2022
	 * @return Page<Distritos>
	 */
	Page<Distrito> findAllByOrderByDescripcionAsc(Pageable pageable);

	/**
	 * Busca y obtiene un {Page<Distritos>} en base al campo descripcion
	 * 
	 * @author brenda.maiz
	 * @since 28.07.2022
	 * @param descripcion
	 * @return Page<Distritos>
	 */
	Page<Distrito> findByDescripcionContainingIgnoreCaseOrderByDescripcionAsc(String descripcion, Pageable pageable);

	/**
	 * Elimina un registro en base al campo id
	 * 
	 * @author brenda.maiz
	 * @since 28.07.2022
	 * @param id
	 */
	void deleteById(Long id);

}