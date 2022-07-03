package br.com.Clinica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClinicaNotFoundException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClinicaNotFoundException(Long id) {
        super("Clinica not found with ID " + id);
    }
}