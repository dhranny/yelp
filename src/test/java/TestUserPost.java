
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import com.yelp.models.Userspost;
@Testable
public class TestUserPost {

	long id;

	@Before
	public void createPost() {
		id = 1l;
	}
	@Test
	public void testId() {
		Userspost testPost = new Userspost();
		testPost.setId(id);
		assertEquals(id, testPost.getId());
	}
}
