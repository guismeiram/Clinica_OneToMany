package br.com.Clinica.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.Clinica.dto.ConsultaDTO;
import br.com.Clinica.dto.ConsultorioDTO;
import br.com.Clinica.exception.ClinicaNotFoundException;
import br.com.Clinica.exception.ResourceNotFoundException;
import br.com.Clinica.model.Consulta;
import br.com.Clinica.model.Consultorio;
import br.com.Clinica.model.Medico;
import br.com.Clinica.repository.ConsultaRepository;

@Service
public class ConsultaService {
	
	private final ConsultaRepository consultaRepository;

	
	@Autowired
	public ConsultaService(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}
	
	 public Consulta createConsulta(Consulta consulta) {

	        return consultaRepository.save(consulta);

	    }
	  public Consulta getConsultaById(long id) throws ClinicaNotFoundException{
	        Optional<Consulta> result = consultaRepository.findById(id);
	        if (result.isPresent()) {
	            return result.get();
	        }else{
	            throw new ResourceNotFoundException();
	        }
	    }

	    public void deleteConsulta(long id)  throws ClinicaNotFoundException{
	        Consulta consulta = consultaRepository.findById(id).
	        		orElseThrow(() -> new ResourceNotFoundException());

	        consultaRepository.delete(consulta);
	    }
	    
	    public Consulta updateById(Consulta consultaRequest,Long id) throws ClinicaNotFoundException{
	        consultaRequest = consultaRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException());

	        //consultorio
	        consultaRequest.getConsultorio().forEach(c ->c.setNumero(c.getNumero()));
	        consultaRequest.getConsultorio().forEach(c ->c.setData_hora(c.getData_hora()));
	        //medico
	        consultaRequest.getMedico().forEach(c ->c.setCrm(c.getCrm()));
	        consultaRequest.getMedico().forEach(c ->c.setEspecialidade(c.getEspecialidade()));
	        consultaRequest.getMedico().forEach(c ->c.setIdade(c.getIdade()));
	        consultaRequest.getMedico().forEach(c ->c.setNome(c.getNome()));
	       
	       return consultaRequest;
	     
	    }
	    
	    /*private String numero;
	private LocalDateTime data_hora;*/
	    
	    /*private String crm;
	private String especialidade;
	private String nome;
	private int idade;*/
}
