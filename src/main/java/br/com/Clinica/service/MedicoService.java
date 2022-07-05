package br.com.Clinica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.Clinica.dto.MedicoDTO;
import br.com.Clinica.exception.ClinicaNotFoundException;
import br.com.Clinica.exception.ResourceNotFoundException;
import br.com.Clinica.model.Consultorio;
import br.com.Clinica.model.Medico;
import br.com.Clinica.repository.MedicoRepository;

@Service
public class MedicoService {
	
	private final MedicoRepository medicoRepository;

	
	@Autowired
	public MedicoService(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

	public Medico createMedico(Medico medico) {
        return medicoRepository.save(medico);
    }
	
	public List<Medico> findAll() {
        return medicoRepository.findAll();

	}

	  public Medico getMedicoById(long id) throws ClinicaNotFoundException{
	        Optional<Medico> result = medicoRepository.findById(id);
	        if (result.isPresent()) {
	            return result.get();
	        }else{
	            throw new ResourceNotFoundException();
	        }
	    }

	    public void deleteMedico(long id)  throws ClinicaNotFoundException{
	        Medico medico = medicoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException());

	        medicoRepository.delete(medico);
	    }
	
	    public Medico updateById(Long id, Medico medicoRequest) throws ClinicaNotFoundException{
	        Medico medico = medicoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException());

	        //imovel.setStatusImovel(imovelRequest.getStatusImovel());
	       medico.setEspecialidade(medicoRequest.getEspecialidade());
	       medico.setCrm(medicoRequest.getCrm());
	       medico.setNome(medicoRequest.getNome());
	       medico.setIdade(medicoRequest.getIdade());
	       
	       return medicoRepository.save(medico);
	    }
	    /*private String crm;
	private String especialidade;
	private String nome;
	private int idade;*/
	
}
