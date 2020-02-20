package springbootexample.app.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@RequestMapping("/topics")
	public List<Topic> allTopics(){
		return Arrays.asList(
				new Topic("spring", "Spring Framework", "Spring Framework description"),
				new Topic("java", "Core java", "Core java description"),
				new Topic("javascript", "javascript", "javascript description"));
		
	}
}
