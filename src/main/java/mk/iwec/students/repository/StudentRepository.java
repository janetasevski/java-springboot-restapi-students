package mk.iwec.students.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import mk.iwec.students.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {
	
}
