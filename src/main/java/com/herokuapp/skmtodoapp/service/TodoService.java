package com.herokuapp.skmtodoapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.Todo;
import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;

	public List<Todo> getTodos(User user) {

		return repository.findByUserAndDeadLineAfterAndCompletedEquals(user, new Date(), false);
	}

	public List<Todo> getTodosByDate(User user, Date date) {

		return repository.findByUserAndDeadLineBefore(user, date);
	}

	public List<Todo> getMissedTodos(User user) {

		return repository.findByUserAndDeadLineBeforeAndCompletedEquals(user, new Date(), false);
	}

	public Todo getTodoById(Integer id) {
		Optional<Todo> todo = repository.findById(id);
		return todo.isPresent() ? todo.get() : null;
	}

	public void addTodo(Todo todo) {
		repository.save(todo);
	}

	public void updateToDo(Todo todo) {

		repository.save(todo);
	}

	public void deleteTodo(Todo todo) {
		repository.delete(todo);
	}

	public void markTodoAsComplete(Todo todo) {
		todo.setCompleted(true);
		repository.save(todo);

	}

	public void markAllTodoAsComplete(User user) {
		List<Todo> todos = repository.findByUserAndCompletedEquals(user, false);
		for (Todo todo : todos) {
			todo.setCompleted(true);
		}
		repository.saveAll(todos);

	}

	public List<Todo> getAllCompletedTodos(User user) {
		return repository.findByUserAndCompletedEquals(user, true);

	}

	public Todo getUpdatableTodo(Integer id) {
		return repository.findOneByIdAndCompletedEquals(id, false);

	}

}
