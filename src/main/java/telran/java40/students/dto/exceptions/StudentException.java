package telran.java40.students.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 89248381445329605L;
//mongo atlas - mongodb
}
