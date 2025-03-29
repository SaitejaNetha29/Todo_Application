package com.project.spring_boot_h2_crud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring_boot_h2_crud.model.Todo;
import com.project.spring_boot_h2_crud.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
	
	@GetMapping("/")
	public String helloWorld() {
		return "hello world";
	}
	
	@GetMapping("/todos")
	public List<Todo> getAllTodos(){
		LOGGER.info("We are inside the GetAll Todo method");
		return todoService.getAllTodos();
	}
	
	@GetMapping("/todo/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		LOGGER.info("We are inside the Get by Id Todo method");
		return ResponseEntity.ok(todoService.getTodoById(id).get());
	}
	
	@PostMapping("/todo")
	public ResponseEntity<Todo> saveTodo(@Valid @RequestBody Todo todo){
		LOGGER.info("We are inside the save Todo method");
		Todo savedTodo = todoService.saveTodo(todo);
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}
	
	@PutMapping("/todo/{id}")
	public ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo, @PathVariable Long id){
		LOGGER.info("We are inside the Update Todo method");
		Todo updatedTodo = todoService.updateTodo(todo,id);
		return ResponseEntity.ok(updatedTodo);
	}
	
	@DeleteMapping("/deleteTodo/{id}")
	public ResponseEntity<Void> deleteTodoById(@PathVariable Long id){
		LOGGER.info("We are inside the Delete Todo method");
		todoService.deleteTodo(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/getTodoByDesc/{description}")
	public Todo getTodoByDescription(@PathVariable String description) {
		return todoService.getTodoByDescription(description);
	}

}
