package com.project.spring_boot_h2_crud.controller;
//used web mock mvc for unit testing controller layer
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.tags.UrlTag;

import com.project.spring_boot_h2_crud.model.Todo;
import com.project.spring_boot_h2_crud.repository.TodoRepository;
import com.project.spring_boot_h2_crud.service.TodoService;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private Todo todo;
	
	@BeforeEach
	void setUp() throws Exception {
		
		todo = Todo.builder().id(1L)
				.description("Have to integrate springboot to frontend")
				.title("Integration")
				.status("Pending")
				.build();
	}

	@Test
	void SaveTodo() throws Exception {
		Todo inputTodo = Todo.builder().id(1L)
				.description("Have to integrate springboot to frontend")
				.title("Integration")
				.status("Pending")
				.build();
		
		Mockito.when(todoService.saveTodo(inputTodo)).thenReturn(todo);
		
		mockMvc.perform(post("/todo")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"description\" : \"Have to integrate springboot to frontend\",\r\n"
						+ "    \"title\" : \"Integration\",\r\n"
						+ "    \"status\" : \"Pending\"\r\n"
						+ "}"))
		.andExpect(status().isOk());
		
	}

}
