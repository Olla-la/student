package telran.java40.students.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class StudentCredentialDto {
	Integer id;
	String name;
	String password;

}
