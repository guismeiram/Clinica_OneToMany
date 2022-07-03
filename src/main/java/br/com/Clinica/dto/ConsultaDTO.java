package br.com.Clinica.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.Clinica.model.Consulta;
import br.com.Clinica.model.Consultorio;
import br.com.Clinica.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id", "medico", "consultorio"})
@Getter
@Setter
@ToString
public class ConsultaDTO  implements Serializable{
	/**
	 * Consulta dto
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private long id;
	@JsonProperty("pessoa")
	private List<Medico> medico = new ArrayList<Medico>();
	@JsonProperty("consultorio")
    private List<Consultorio> consultorio = new ArrayList<Consultorio>();
	
	
}
