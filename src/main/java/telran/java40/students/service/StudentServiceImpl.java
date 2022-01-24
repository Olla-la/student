package telran.java40.students.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import telran.java40.students.dao.StudentsRepository;
import telran.java40.students.dto.ScoreDto;
import telran.java40.students.dto.StudentCredentialDto;
import telran.java40.students.dto.StudentDto;
import telran.java40.students.dto.UpdateStudentDto;
import telran.java40.students.dto.exceptions.StudentException;
import telran.java40.students.model.Student;

//@Component  //@Component gives entrance to appl.context or via ModelMapping( modelMapper app/
@Service
public class StudentServiceImpl implements StudentService {
	
	StudentsRepository studentRepository;
	ModelMapper modelMapper;
	
	
	/// pass to progect olga: 2312.com at MongoAtlas
	
	@Autowired
	public StudentServiceImpl(StudentsRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper=modelMapper;
	}

	@Override
	public boolean addStudent(StudentCredentialDto studentCredentialDto) {
		if(studentRepository.existsById(studentCredentialDto.getId())) {
			return false;
		}
		Student student = modelMapper.map(studentCredentialDto, Student.class);
		studentRepository.save(student);
		return true;}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student =studentRepository.findById(id).orElseThrow(StudentException::new);
		return  modelMapper.map(student, StudentDto.class);
		}

	@Override
	public StudentDto deleteStudent(Integer id) {
		Student student =studentRepository.findById(id).orElseThrow(StudentException::new);
		studentRepository.deleteById(id);
		return modelMapper.map(student, StudentDto.class);
		}
	
	@Override
	public StudentCredentialDto updateStudent(Integer id, UpdateStudentDto studentDto) {
		Student student =studentRepository.findById(id).orElseThrow(StudentException::new);
		student.setName(studentDto.getName());
		student.setPassword(studentDto.getPassword());
		studentRepository.save(student);
		return modelMapper.map(student, StudentCredentialDto.class);
		}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		boolean res = true;
		Student student =studentRepository.findById(id).orElseThrow(StudentException::new);
		if(student.getScores().containsKey(scoreDto.getExamName())) {
			res = false;
			}
		student.getScores().put(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return res;
		}

		/*
		 * @Override public List<StudentDto> findStudentByName(String name) { return
		 * studentRepository.findAll().stream()
		 * .filter(j->j.getName().equalsIgnoreCase(name)) .map(a->modelMapper.map(a,
		 * StudentDto.class)) .collect(Collectors.toList()); }
		 */
	
	@Override
	public List<StudentDto> findStudentByName(String name){
		return studentRepository.findByNameIgnoreCase(name)
				.map(s->modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public long getStudentsGetQuantity(List<String> names) {
		return studentRepository.countByNameInIgnoreCase(names);
		
		/*
		 * long count = 0; Set <String>names2= new
		 * TreeSet<>(String.CASE_INSENSITIVE_ORDER); names2.addAll(names); for(String
		 * name: names2) { count += studentRepository.findAll().stream()
		 * .map(m->m.getName()) .filter(j->j.equalsIgnoreCase(name)) .count(); } return
		 * count;
		 */
	}


	@Override
	public List<StudentDto> getStudentsByExamScore(String exam, int min) {
		return studentRepository.findByExamAndScoreGreateEqualsThan(exam, min)
				.map(s->modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
		
		/*return  studentRepository.findAll().stream()
				.filter(l->l.getScores().containsKey(exam.toLowerCase()))
				.filter(j->j.getScores().get(exam.toLowerCase())>min)
				.map(a->modelMapper.map(a, StudentDto.class))
				.collect(Collectors.toList());
		}*/
	}
}
	
