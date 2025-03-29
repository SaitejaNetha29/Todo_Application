package com.project.spring_boot_h2_crud.service;

import java.util.List;
import java.util.Optional;

import com.project.spring_boot_h2_crud.model.Todo;

public interface TodoService {

	List<Todo> getAllTodos();

	Optional<Todo> getTodoById(Long id);

	Todo saveTodo(Todo todo);

	Todo updateTodo(Todo todo, Long id);

	void deleteTodo(Long id);

	Todo getTodoByDescription(String description);

}
