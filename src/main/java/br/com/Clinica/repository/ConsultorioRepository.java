package br.com.Clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Clinica.model.Consultorio;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio,Long>{

}
