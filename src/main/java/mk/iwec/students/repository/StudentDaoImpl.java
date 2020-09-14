package mk.iwec.students.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mk.iwec.students.model.Student;

@Slf4j
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	EntityManager em;

	@Override
	public List<Student> findAll() {
//		Get all students and insert to list JPQL or HQL Way
//		List<Student> students = em.createQuery("from Student", Student.class).getResultList();
		
		
//		With namedquery way
//		List<Student> students = em.createNamedQuery("get all students", Student.class).getResultList();
		
		
// 		This is with criteria query way
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		Root<Student> root = cq.from(Student.class);
//		Predicates 1 way
//		Predicate[] predicates = new Predicate[2];
//		predicates[0] = cb.isNotNull(root.get("lastName"));
//		predicates[1] = cb.like(root.get("firstName"), "K%");
//		cq.select(root).where(predicates);
		
//		Predicated 2 way
//		Predicate pNotNull = cb.isNotNull(root.get("lastName"));
//		Predicate pLike = cb.like(root.get("firstName"), "K%");
//		cq.select(root).where(cb.or(pNotNull,pLike));
//		cq.select(root).where(cb.and(pNotNull,pLike));
		
		cq.select(root);
		TypedQuery<Student> tq = em.createQuery(cq);
		List<Student> students = tq.getResultList();
		
		return students;
	}

	@Override
	public Student findById(Integer id) {
		Student student = em.find(Student.class, id);
		return student == null ? new Student() : student;
	}

	@Override
	public void save(Student student) {
		em.persist(student);
		log.info("Persisting Student entity with id: {}", student.getId());
	}

	@Override
	public void update(Student student) {
		Student studentPersisted = em.find(Student.class, student.getId());
		studentPersisted.setFirstName(student.getFirstName());
		studentPersisted.setLastName(student.getLastName());
		em.merge(studentPersisted);
		log.info("Updating Student entity with id: {}", student.getId());
	}

	@Override
	public void delete(Integer id) {
//		HQL Way
//		final String HQL = "delete Student where id = :id";
//		Query q = em.createQuery(HQL).setParameter("id", id);
//		q.executeUpdate();
		
//		This is with named query way
//		Here i send first name like example for optional parameter
		em.createNamedQuery("delete student by id").setParameter("id", id).setParameter("firstName", null).executeUpdate();
		log.info("Deleting Student entity with id: {}", id);
	}

}
