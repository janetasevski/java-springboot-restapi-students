package mk.iwec.students.service;

import java.util.List;

public interface GenericService<T> {
	public List<T> findAll();
	public T findById(Integer id);
	public void save(T t);
	public void update(T t);
	public void delete(Integer id);
}
