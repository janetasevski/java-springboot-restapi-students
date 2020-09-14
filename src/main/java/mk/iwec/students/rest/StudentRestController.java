package mk.iwec.students.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.iwec.students.model.Student;
import mk.iwec.students.service.StudentServiceForRepository;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentRestController {

	@Autowired
	//first interface is for StudentService for DAO implementation
	//seccond interface is for Spring Data Repository
	//private StudentService service;
	private StudentServiceForRepository service;

	@GetMapping
	public List<Student> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Student findById(@PathVariable(value = "id") Integer id) {
		Student student = service.findById(id);
		if (student == null) {
			throw new RuntimeException("Student with id " + id + " is not found.");
		}
		return student;
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Student save(@RequestBody Student student) {
		return service.save(student);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Student update(@RequestBody Student student) {
		return service.update(student);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Integer id) {
		//here we also can return boolean instead void
//		if (id == null) {
//			throw new RuntimeException("Student with id null is not allowed.");
//		}
		service.delete(id);
	}
}
