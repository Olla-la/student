package telran.java40.students.service;

import java.util.List;

import telran.java40.students.dto.ScoreDto;
import telran.java40.students.dto.StudentCredentialDto;
import telran.java40.students.dto.StudentDto;
import telran.java40.students.dto.UpdateStudentDto;

public interface StudentService {
	
	boolean addStudent(StudentCredentialDto studentDto);
	StudentDto findStudent(Integer id);
	StudentDto deleteStudent(Integer id);
	StudentCredentialDto updateStudent(Integer id, UpdateStudentDto studentDto);
	boolean addScore(Integer id, ScoreDto scoreDto);
	long getStudentsGetQuantity(List<String>names);
	List<StudentDto> findStudentByName(String name);
	//if the object has fields (has sostoyanie(!) - need synchonization, but for Correct servlet should not be fields - only sostioyanie-less)
	List<StudentDto> getStudentsByExamScore(String exam, int min);

}
