package mk.iwec.students.service;

import java.util.List;

import mk.iwec.students.model.Student;

public interface StudentServiceForRepository {
	public List<Student> findAll();
	public Student findById(Integer id);
	public Student save(Student student);
	public Student update(Student student);
	public void delete(Integer id);
}
