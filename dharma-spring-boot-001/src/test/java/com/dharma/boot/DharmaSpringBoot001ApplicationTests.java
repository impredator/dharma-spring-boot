package com.dharma.boot;

import com.dharma.boot.controller.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DharmaSpringBoot001ApplicationTests {

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		RequestBuilder request = get("/index");
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("What's up"));

		request = get("/index/get").param("name", "R1");
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("{\"trivial\":null,\"build\":0,\"name\":\"R1\",\"project\":null,\"description\":null,\"admin\":null,\"team\":null,\"title\":null}"));
	}

}
