package mk.iwec.students.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "students")
//Here i send first name like example for optional parameter
@NamedQuery(query = "delete Student s where s.id = :id AND (:firstName is null OR s.firstName = :firstName)", name = "delete student by id")
@NamedQuery(query = "from Student", name = "get all students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	//id ke bidi autoincrement
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;
	
	@Version
	@Column(name = "OPTLOCK")
	private Integer version;
	
	//dokolku odredeno properti vo modelot ni postoi no nesakame da go imame vo baza se koristi anotacijata @Transient

}
