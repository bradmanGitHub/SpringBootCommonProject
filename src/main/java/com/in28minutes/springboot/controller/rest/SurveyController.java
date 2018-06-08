package com.in28minutes.springboot.controller.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId){
		return surveyService.retrieveQuestions(surveyId);
	}
	
	@PostMapping("surveys/{surveyId}/questions")
	public ResponseEntity<?> addQuestionsToSurvey(@PathVariable String surveyId, @RequestBody Question question){
		
		/*
		 *     "id": "Question1",
			    "description": "Largest Country in the World",
			    "correctAnswer": "Russia",
			    "options": [
			        "India",
			        "Russia",
			        "United States",
			        "China"
			    ]
		 */
		
		 Question createdTodo = surveyService.addQuestion(surveyId, question);

	        if (createdTodo == null) {
	            return ResponseEntity.noContent().build();
	        }

	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

	        return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestions(@PathVariable String surveyId, @PathVariable String questionId){
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
}
