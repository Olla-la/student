package telran.java40.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java40.students.dto.ScoreDto;
import telran.java40.students.dto.StudentCredentialDto;
import telran.java40.students.dto.StudentDto;
import telran.java40.students.dto.UpdateStudentDto;
import telran.java40.students.service.StudentService;

@RestController
public class StudentController {
	StudentService studentService;

@Autowired
public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

@PostMapping("/student") //endPoint
public boolean studentRegister(@RequestBody StudentCredentialDto student) {
	return studentService.addStudent(student);
};

@GetMapping("/student/{id}")
public StudentDto findStudentById(@PathVariable("id") Integer id) {//if name and param coincide - no need to add("id") after Path
	return studentService.findStudent(id);
}

@DeleteMapping("/student/{id}")
public StudentDto removeStudent(@PathVariable("id") Integer id) {//if name and param coincide - no need to add("id") after Path
	return studentService.deleteStudent(id);
}

@PutMapping("/student/{id}")
public StudentCredentialDto editStudent(@PathVariable Integer id, @RequestBody UpdateStudentDto studentDto) {
	return studentService.updateStudent(id, studentDto);
}

@PutMapping("/score/student/{id}")
public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
	return studentService.addScore(id, scoreDto);
}

@GetMapping("/students/name/{name}")
public List<StudentDto> findStudentsByName(@PathVariable String name){
	return studentService.findStudentByName(name);
}

@PostMapping("/quantity/students")
public long getStudentsGetQuantity(@RequestBody List<String>names) {
	return studentService.getStudentsGetQuantity(names);
}

@GetMapping("/students/exam/{exam}/minscore/{min}")
public List<StudentDto> getStudentsByExamScore(@PathVariable String exam, @PathVariable int min) {
	return studentService.getStudentsByExamScore(exam, min);
}

}
