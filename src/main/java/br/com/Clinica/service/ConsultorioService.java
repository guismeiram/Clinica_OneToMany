package br.com.Clinica.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.Clinica.dto.ConsultorioDTO;
import br.com.Clinica.dto.MedicoDTO;
import br.com.Clinica.exception.ClinicaNotFoundException;
import br.com.Clinica.exception.ResourceNotFoundException;
import br.com.Clinica.model.Consultorio;
import br.com.Clinica.model.Medico;
import br.com.Clinica.repository.ConsultorioRepository;

@Service
public class ConsultorioService {
	
	private final ConsultorioRepository consultorioRepository;

	
	@Autowired
	public ConsultorioService(ConsultorioRepository consultorioRepository) {
		this.consultorioRepository = consultorioRepository;
	}
	
	public List<Consultorio> findAll() {
        return consultorioRepository.findAll();

	}
	
	public Consultorio createConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }
	
	

	  public Consultorio getConsultorioById(long id) throws ClinicaNotFoundException{
	        Optional<Consultorio> result = consultorioRepository.findById(id);
	        if (result.isPresent()) {
	            return result.get();
	        }else{
	            throw new ResourceNotFoundException();
	        }
	    }

	    public void deleteConsultorio(long id)  throws ClinicaNotFoundException{
	        Consultorio consultorio = consultorioRepository.findById(id).
	        		orElseThrow(() -> new ResourceNotFoundException());

	        consultorioRepository.delete(consultorio);
	    }
	    
	    public Consultorio updateById(Long id, Consultorio consultorioRequest) throws ClinicaNotFoundException{
	        Consultorio consultorio = consultorioRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException());

	        //imovel.setStatusImovel(imovelRequest.getStatusImovel());
	       consultorio.setNumero(consultorioRequest.getNumero());
	       consultorio.setData_hora(consultorioRequest.getData_hora());
	       
	       return consultorioRepository.save(consultorio);
	     
	    }
	/*private String numero;
	private LocalDateTime data_hora;*/
}
