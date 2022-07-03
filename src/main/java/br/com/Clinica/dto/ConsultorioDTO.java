package br.com.Clinica.dto;

import java.io.Serializable;
import java.time.LocalDateTime;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.Clinica.model.Consulta;
import br.com.Clinica.model.Consultorio;
import br.com.Clinica.model.Medico;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","numero","data_hora","medico"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConsultorioDTO  implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private long id;
	@JsonProperty("numero")
	private String numero;
	@JsonProperty("data_hora")
	private LocalDateTime data_hora;

	

		
		
}