package mk.iwec.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.iwec.students.model.Student;
import mk.iwec.students.repository.StudentDao;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao repository;

	@Override
	public List<Student> findAll() {
		return repository.findAll();
	}

	@Override
	public Student findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void save(Student student) {
		repository.save(student);
	}

	@Override
	public void update(Student student) {
		repository.update(student);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

}
