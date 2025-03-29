package com.project.spring_boot_h2_crud.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring_boot_h2_crud.model.Todo;
import com.project.spring_boot_h2_crud.repository.TodoRepository;
import com.project.spring_boot_h2_crud.exception.ResourceNotFoundException;
@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Optional<Todo> getTodoById(Long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		if(! todo.isPresent()) {
			 throw new ResourceNotFoundException("Todo not found");
		}
		return todo;
	}

	@Override
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo updateTodo(Todo todo, Long id) {
		Todo db = todoRepository.findById(id).get();
		if(Objects.isNull(db)) {
			throw new ResourceNotFoundException("Resource not found for id" + id);
		}
		if(Objects.nonNull(todo.getDescription()) && !"".equalsIgnoreCase(todo.getDescription())) {
			db.setDescription(todo.getDescription());
		}
		if(Objects.nonNull(todo.getStatus()) && !"".equalsIgnoreCase(todo.getStatus())) {
			db.setStatus(todo.getStatus());
		}
		if(Objects.nonNull(todo.getTitle()) && !"".equalsIgnoreCase(todo.getTitle())) {
			db.setTitle(todo.getTitle());
		}
		return todoRepository.save(db);
	}

	@Override
	public void deleteTodo(Long id) {
		Todo db = todoRepository.findById(id).get();
		if(Objects.isNull(db)) {
			throw new ResourceNotFoundException("Resource not found for id" + id);
		}
		todoRepository.deleteById(id);
	}

	@Override
	public Todo getTodoByDescription(String description) {
		return todoRepository.getTodoByDescription(description);
	}

}
