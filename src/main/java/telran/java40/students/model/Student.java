package telran.java40.students.model;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class Student {
	Integer id;
	@Setter
	String name;
	@Setter
	String password;
	@Setter
	Map<String, Integer> scores = new HashMap<>();
	
	public Student(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public boolean addScore(String exam, int score) {
		return scores.put(exam, score)==null;
				}
}
