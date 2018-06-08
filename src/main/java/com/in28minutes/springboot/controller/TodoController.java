package com.in28minutes.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.model.Todo;
import com.in28minutes.springboot.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	//Injected automatically
		@Autowired
		TodoService service;
		
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			// Date - dd/MM/yyyy
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(
					dateFormat, false));
		}
		
		@RequestMapping(value="/list-todos", method=RequestMethod.GET)
		//@ResponseBody
		public String showTodos(ModelMap model) {
			String name = getLoggedInUserName(model);
			model.addAttribute("todos", service.retrieveTodos(name));
			return "list-todos";
		}

		private String getLoggedInUserName(ModelMap model) {
//			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			if(principal instanceof UserDetails) {
//				return ((UserDetails) principal).getUsername();
//			}
//			return principal.toString();
			return "admin";
		}
		
		@RequestMapping(value="/add-todo", method=RequestMethod.GET)
		public String showAddTodos(ModelMap model) {
			model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "", new Date(), false));
			return "todo";
		}

		@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
		public String deleteTodo(@RequestParam int id) {
			if(id == 1) {
				throw new RuntimeException("Something went wrong");
			}
			service.deleteTodo(id);
			return "redirect:/list-todos";
		}
		
		@RequestMapping(value="/update-todo", method=RequestMethod.GET)
		public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
			Todo todo = service.retrieveTodo(id);
			model.addAttribute("todo", todo);
			return "todo";
		}
		
		@RequestMapping(value="/update-todo", method=RequestMethod.POST)
		public String updateTodo(@Valid Todo todo, BindingResult result) {
			if(result.hasErrors()) {
				return "todo";
			}
			service.updateTodo(todo);
			return "redirect:/list-todos";
		}
		
		@RequestMapping(value="/add-todo", method=RequestMethod.POST)
		//@ResponseBody
		public String addTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
			if(result.hasErrors()) {
				return "todo";
			}
			String name = getLoggedInUserName(model);
			service.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
			return "redirect:/list-todos";
		}

}
