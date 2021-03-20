package wharf.studio.realworld.api;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersApiTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void able_to_register() throws Exception {		
		ObjectNode user = objectMapper.createObjectNode();
		user.putObject("user")
			.put("email", "jake@jake.jake")
			.put("username", "Jacob")
			.put("password", "jakejake");
				
		String payload = user.toString();
		
		mockMvc.perform(post("/users")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(payload))
			   .andDo(print())
			   .andExpect(status().isCreated())
			   .andExpect(jsonPath("$.user.token").exists())
			   .andExpect(jsonPath("$.user.username").value("Jacob"));
		
	}
}
