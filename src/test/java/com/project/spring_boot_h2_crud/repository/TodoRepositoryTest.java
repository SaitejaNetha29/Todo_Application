package com.project.spring_boot_h2_crud.repository;
//used entity manager test as it persists and flushes out test data after method is done running
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.project.spring_boot_h2_crud.model.Todo;

@DataJpaTest
class TodoRepositoryTest {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp() {
		Todo todo = Todo.builder().description("Have to integrate springboot to frontend")
				.title("Integration")
				.status("Pending")
				.build();
		entityManager.persist(todo);
	}

	@Test
	@DisplayName("Get Data with valid id")
	public void  whenFindById_thenReturnDepartment() {
		Todo todo = todoRepository.findById(1L).get();
		assertEquals(todo.getTitle(), "Integration");
	}

}
