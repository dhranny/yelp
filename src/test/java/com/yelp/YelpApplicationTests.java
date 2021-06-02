package com.yelp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.controller.ContentUploadController;
import com.yelp.models.UsersPost;

@RunWith(SpringRunner.class)
@WebMvcTest(ContentUploadController.class)
@SpringBootTest
class YelpApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void shouldReturnOk() {
		UsersPost post = new UsersPost();
		
		try {
			mockMvc.perform(post("/contentUploads")
					.content(mapper.writeValueAsString(post))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isAccepted());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
