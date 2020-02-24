package springbootexample.app.topic;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {

}
