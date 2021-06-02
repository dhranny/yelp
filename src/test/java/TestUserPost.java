
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.boot.test.context.SpringBootTest;

import com.yelp.models.UsersPost;
@SpringBootTest
public class TestUserPost {

	long id;

	@Before
	public void createPost() {
		id = 1l;
	}
	@Test
	public void testId() {
		UsersPost testPost = new UsersPost();
		testPost.setId(id);
		assertEquals(id, testPost.getId());
	}
}
