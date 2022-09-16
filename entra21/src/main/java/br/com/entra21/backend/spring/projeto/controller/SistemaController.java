package br.com.entra21.backend.spring.projeto.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sistema")
public class SistemaController {
	 @GetMapping()

	 @ResponseStatus(HttpStatus.OK)

	 public String on(Model model) {

	 return "Estou ON";

	 }
	 
	 @GetMapping("merendar")
	    @ResponseStatus(HttpStatus.ACCEPTED)
	    
	    public boolean merendar() {
	        return true;
	    }
}
