package br.com.Clinica.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.Clinica.dto.ConsultaDTO;
import br.com.Clinica.dto.ConsultorioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","pessoa","consultorio"})

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "TB_CONSULTA")
public class Consulta implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "consulta")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Medico> medico = new ArrayList<Medico>();
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "consulta")
	@LazyCollection(LazyCollectionOption.FALSE)
    private List<Consultorio> consultorio = new ArrayList<Consultorio>();

	public Long getAllId(){
		return (id.equals(0) ? null:id);
	}


}
