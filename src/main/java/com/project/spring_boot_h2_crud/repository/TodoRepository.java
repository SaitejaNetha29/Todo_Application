package com.project.spring_boot_h2_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_boot_h2_crud.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

	public Todo getTodoByDescription(String description);

}
