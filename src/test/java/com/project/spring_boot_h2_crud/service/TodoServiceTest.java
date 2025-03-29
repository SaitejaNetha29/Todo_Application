package com.project.spring_boot_h2_crud.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.project.spring_boot_h2_crud.model.Todo;
import com.project.spring_boot_h2_crud.repository.TodoRepository;

@SpringBootTest
class TodoServiceTest {
	@Autowired
	private TodoService todoService;
	
	@MockitoBean
	private TodoRepository todoRepository;

	@BeforeEach
	void setUp() {
		Todo todo = Todo.builder().id(1L)
				.description("I have to complete springboot today")
				.title("Spring boot Mocking")
				.status("Inprogress ").build();
		
		Mockito.when(todoRepository.getTodoByDescription("I have to complete springboot today")).thenReturn(todo);
	}
	@Test
	@DisplayName("Get data based on valid description")
	public void whenValidDescription_thenTodoListFound() {
		String desc = "I have to complete springboot today";
		 Todo found = todoService.getTodoByDescription(desc);
		 
		 assertEquals(desc, found.getDescription());
	}

}
