package com.herokuapp.skmtodoapp.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.herokuapp.skmtodoapp.entity.Todo;
import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.exception.model.BadArgumentException;
import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;
import com.herokuapp.skmtodoapp.service.TodoService;
import com.herokuapp.skmtodoapp.service.UserService;

@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@Autowired
	private SimpleDateFormat dateFormat;

	@Autowired
	private UserService userService;
	

	@RequestMapping
	public String defaultHome() {
		return "redirect:/todo/upcoming";
	}

	@RequestMapping("/upcoming")
	public String showUpcoming(Model model, HttpSession session) throws Exception {
	
		User user = userService.getUserBean();

		model.addAttribute("todos", todoService.getTodos(user));
		model.addAttribute("error", session.getAttribute("error"));
		model.addAttribute("warning", session.getAttribute("warning"));
		model.addAttribute("success", session.getAttribute("success"));
		model.addAttribute("dateFormatter", dateFormat);
		session.removeAttribute("error");
		session.removeAttribute("warning");
		session.removeAttribute("success");
		return "upcoming";
	}

	@RequestMapping("/missed")
	public String showMissed(Model model, HttpSession session) throws UserNotFoundException {
		
		User user = userService.getUserBean();
		
		model.addAttribute("todos", todoService.getMissedTodos(user));
		model.addAttribute("error", session.getAttribute("error"));
		model.addAttribute("warning", session.getAttribute("warning"));
		model.addAttribute("success", session.getAttribute("success"));
		model.addAttribute("dateFormatter", dateFormat);
		session.removeAttribute("error");
		session.removeAttribute("warning");
		session.removeAttribute("success");
		return "missed";
	}

	@RequestMapping("/completed")
	public String showCompleted(Model model, HttpSession session) throws UserNotFoundException {
		
		User user = userService.getUserBean();
		
		model.addAttribute("todos", todoService.getAllCompletedTodos(user));
		model.addAttribute("error", session.getAttribute("error"));
		model.addAttribute("warning", session.getAttribute("warning"));
		model.addAttribute("success", session.getAttribute("success"));
		model.addAttribute("dateFormatter", dateFormat);
		session.removeAttribute("error");
		session.removeAttribute("warning");
		session.removeAttribute("success");
		return "completed";
	}

	@RequestMapping("/add")
	public String showAddTodoForm() {
		return "add-todo";
	}

	@PostMapping("/addTodo")
	public String addTodo(@ModelAttribute Todo todo, @RequestParam("date") String date, HttpSession session)
			throws Exception {

		todo.setDeadLine(dateFormat.parse(date));
		
		User user = userService.getUserBean();

		todo.setId(0);
		todo.setCompleted(false);
		todo.setUser(user);

		todoService.addTodo(todo);
		return "redirect:/todo";

	}

	@RequestMapping("/update")
	public String showUpdateTodoForm(@RequestParam("id") Integer id, HttpSession session, Model model)
			throws Exception {
		Todo todo = todoService.getTodoById(id);
		if (todo == null) {
			throw new BadArgumentException("can't update the todo");
		}
		model.addAttribute("todo", todo);
		model.addAttribute("dateFormatter", dateFormat);
		return "update-todo";
	}

	@PostMapping("/updateTodo")
	public String updateTodo(@ModelAttribute Todo todo, @RequestParam("date") String date, HttpSession session)
			throws Exception {
		todo.setDeadLine(dateFormat.parse(date));
		todoService.updateToDo(todo);
		return "redirect:/todo";

	}

	@RequestMapping("/markComplete")
	public String markTodoComplete(@RequestParam("id") Integer id) throws Exception {

		Todo todo = todoService.getTodoById(id);
		if (todo.getCompleted() == true) {
			throw new BadArgumentException("This todo is already complete");
		}
		todoService.markTodoAsComplete(todo);
		return "redirect:/todo";

	}

	@RequestMapping("/markComplete/all")
	public String markAllTodoComplete() throws Exception {
		
		User user = userService.getUserBean();

		todoService.markAllTodoAsComplete(user);
		return "redirect:/todo";

	}

	@RequestMapping("/delete")
	public String deleteTodo(@RequestParam("id") Integer id) throws Exception {

		Todo todo = todoService.getTodoById(id);
		if (todo == null) {
			throw new BadArgumentException("Todo with id" + id + "not found");
		}
		todoService.deleteTodo(todo);
		return "redirect:/todo";
	}
}
