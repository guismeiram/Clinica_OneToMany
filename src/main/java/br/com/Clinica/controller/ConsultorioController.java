package br.com.Clinica.controller;



import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.Clinica.dto.ConsultorioDTO;
import br.com.Clinica.dto.MedicoDTO;
import br.com.Clinica.exception.ClinicaNotFoundException;
import br.com.Clinica.model.Consultorio;
import br.com.Clinica.model.Medico;
import br.com.Clinica.service.ConsultorioService;

@RestController
@RequestMapping("/api/consultorio")
public class ConsultorioController {
	
	private final ConsultorioService consultorioService;

	
	@Autowired
	public ConsultorioController(ConsultorioService consultorioService) {
		this.consultorioService = consultorioService;
	}
	

	 @Autowired
	 private ModelMapper modelMapper;

	
	 	@PostMapping
	    public ResponseEntity<ConsultorioDTO> createConsultorio(@RequestBody ConsultorioDTO consultorioDTO) {
	        // convert DTO to entity
	        Consultorio consultorioRequest = modelMapper.map(consultorioDTO, Consultorio.class);

	        Consultorio consultorio = consultorioService.createConsultorio(consultorioRequest);

	        // convert entity to DTO
	        ConsultorioDTO consultorioResponse = modelMapper.map(consultorio, ConsultorioDTO.class);

	        return new ResponseEntity<ConsultorioDTO>(consultorioResponse, HttpStatus.CREATED);
	    }
	
	 @GetMapping
	    public List<ConsultorioDTO> getAllConsultorios() {

	        return consultorioService.findAll().stream().map(post -> modelMapper.map(post, ConsultorioDTO.class))
	                .collect(Collectors.toList());
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<ConsultorioDTO> getConsultorioById(@PathVariable(name = "id") Long id) throws ClinicaNotFoundException{
	         Consultorio consultorio = consultorioService.getConsultorioById(id);

	        // convert entity to DTO
	        ConsultorioDTO consultorioResponse = modelMapper.map(consultorio, ConsultorioDTO.class);

	        return ResponseEntity.ok().body(consultorioResponse);
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deletePost(@PathVariable(name = "id") Long id) throws ClinicaNotFoundException {
	        consultorioService.deleteConsultorio(id);

	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ConsultorioDTO> updateImovel(@RequestBody ConsultorioDTO consultorioDTO, @PathVariable long id) throws ClinicaNotFoundException {
	        Consultorio consultorioRequest = modelMapper.map(consultorioDTO, Consultorio.class);

	        Consultorio consultorio = consultorioService.updateById(id, consultorioRequest);

	        // convert entity to DTO
	        ConsultorioDTO consultorioResponse = modelMapper.map(consultorio, ConsultorioDTO.class);

	        return ResponseEntity.ok().body(consultorioResponse);
	    }

	
	
}
