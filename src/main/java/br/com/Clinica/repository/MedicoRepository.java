package br.com.Clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Clinica.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long>{

}
