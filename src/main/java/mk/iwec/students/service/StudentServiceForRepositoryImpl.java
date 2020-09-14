package mk.iwec.students.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.iwec.students.model.Student;
import mk.iwec.students.repository.StudentRepository;

@Service
public class StudentServiceForRepositoryImpl implements StudentServiceForRepository {
	
	@Autowired
	StudentRepository repository;
	
	@Override
	public List<Student> findAll() {
		Iterable<Student> iterable = repository.findAll();
		List<Student> students = new ArrayList<>();
		iterable.forEach(students::add);
		return students;
	}

	@Override
	public Student findById(Integer id) {
		Optional<Student> opt = repository.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public Student save(Student student) {
		return repository.save(student);
	}

	@Override
	public Student update(Student student) {
		Optional<Student> opt = repository.findById(student.getId());
		if (!opt.isPresent()) {
			return null;
		}
		Student studentEntity = opt.get();
		studentEntity.setFirstName(student.getFirstName());
		studentEntity.setLastName(student.getLastName());
		return repository.save(studentEntity);
	}

	@Override
	public void delete(Integer id) {
		Optional<Student> opt = repository.findById(id);
		opt.ifPresent(o -> repository.delete(o));
	}

}
