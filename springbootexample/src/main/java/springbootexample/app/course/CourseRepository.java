package springbootexample.app.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {

	public List<Course> findByTopicId(String topicId);
	//when writing a special name, JPA knows what we mean
}
