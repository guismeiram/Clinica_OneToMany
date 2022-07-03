package br.com.Clinica.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.Clinica.dto.MedicoDTO;
import br.com.Clinica.exception.ClinicaNotFoundException;
import br.com.Clinica.model.Medico;
import br.com.Clinica.service.MedicoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {
	
	private final MedicoService medicoService;
	
	 @Autowired
	 private ModelMapper modelMapper;
	 
	
	 public MedicoController(MedicoService medicoService, ModelMapper modelMapper) {
		super();
		this.medicoService = medicoService;
		this.modelMapper = modelMapper;
	}


		@PostMapping
	    public ResponseEntity<MedicoDTO> createMedico(@RequestBody MedicoDTO medicoDTO) {
	        // convert DTO to entity
	        Medico medicoRequest = modelMapper.map(medicoDTO, Medico.class);

	        Medico medico = medicoService.createMedico(medicoRequest);

	        // convert entity to DTO
	        MedicoDTO medicoResponse = modelMapper.map(medico, MedicoDTO.class);

	        return new ResponseEntity<MedicoDTO>(medicoResponse, HttpStatus.CREATED);
	    }
	
	
	
	
	
	 @GetMapping
	    public List<MedicoDTO> getAllMedicos() {

	        return medicoService.findAll().stream().map(post -> modelMapper.map(post, MedicoDTO.class))
	                .collect(Collectors.toList());
	    }
	 
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable(name = "id") Long id) throws ClinicaNotFoundException{
	        Medico medico = medicoService.getMedicoById(id);

	        // convert entity to DTO
	        MedicoDTO medicoResponse = modelMapper.map(medico, MedicoDTO.class);

	        return ResponseEntity.ok().body(medicoResponse);
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deletePost(@PathVariable(name = "id") Long id) throws ClinicaNotFoundException {
	        medicoService.deleteMedico(id);

	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<MedicoDTO> updateImovel(@RequestBody MedicoDTO medicoDTO, @PathVariable long id) throws ClinicaNotFoundException {
	        Medico medicoRequest = modelMapper.map(medicoDTO, Medico.class);

	        Medico medico = medicoService.updateById(id, medicoRequest);

	        // convert entity to DTO
	        MedicoDTO medicoResponse = modelMapper.map(medico, MedicoDTO.class);

	        return ResponseEntity.ok().body(medicoResponse);
	    }

	 
	
}
