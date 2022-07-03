package br.com.Clinica.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.Clinica.dto.ConsultaDTO;
import br.com.Clinica.dto.MedicoDTO;
import br.com.Clinica.exception.ClinicaNotFoundException;
import br.com.Clinica.model.Consulta;
import br.com.Clinica.service.ConsultaService;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
	
	@Autowired
	private final ConsultaService consultaService;
	
	 @Autowired
	private ModelMapper mapper;
	 
	 public ConsultaController(ConsultaService consultaService, ModelMapper mapper) {
			this.consultaService = consultaService;
			this.mapper = mapper;
		}
	 
	 
	
     
	 	@PostMapping
		@Transactional
		public ConsultaDTO createConsulta(@RequestBody ConsultaDTO consultaDTO)
		{
			Consulta consultaRequest = mapper.map(consultaDTO, Consulta.class);
			//consultorio
			consultaRequest.getConsultorio().forEach(c ->c.setNumero(c.getNumero()));
			consultaRequest.getConsultorio().forEach(c ->c.setData_hora(c.getData_hora()));
			//medico
			consultaRequest.getMedico().forEach(c ->c.setCrm(c.getCrm()));
			consultaRequest.getMedico().forEach(c ->c.setEspecialidade(c.getEspecialidade()));
			consultaRequest.getMedico().forEach(c ->c.setIdade(c.getIdade()));
			consultaRequest.getMedico().forEach(c ->c.setNome(c.getNome()));

			//Grava na Memoria
			Consulta consulta = consultaService.createConsulta(consultaRequest);
			// convert entity to DTO
			return mapper.map(consulta, ConsultaDTO.class);
		}
     
	    @GetMapping("/{id}")
	    public ResponseEntity<ConsultaDTO> getPostById(@PathVariable(name = "id") Long id) throws ClinicaNotFoundException{
	        Consulta consulta = consultaService.getConsultaById(id);

	        // convert entity to DTO
	        ConsultaDTO consultaResponse = mapper.map(consulta, ConsultaDTO.class);

	        return ResponseEntity.ok().body(consultaResponse);
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deletePost(@PathVariable(name = "id") Long id) throws ClinicaNotFoundException {
	        consultaService.deleteConsulta(id);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ConsultaDTO> updateImovel(@RequestBody ConsultaDTO consultaDTO, @PathVariable long id) throws ClinicaNotFoundException {
	        Consulta consultaRequest = mapper.map(consultaDTO, Consulta.class);

	        Consulta consulta = consultaService.updateById(consultaRequest, id);

	        // convert entity to DTO
	        ConsultaDTO consultaResponse = mapper.map(consulta, ConsultaDTO.class);

	        return ResponseEntity.ok().body(consultaResponse);
	    }
	 	
}
