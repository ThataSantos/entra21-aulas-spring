package br.com.entra21.backend.spring.projeto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.entra21.backend.spring.projeto.model.ItemNivel3;
import br.com.entra21.backend.spring.projeto.model.Personagem;
import br.com.entra21.backend.spring.projeto.model.Usuario;
import br.com.entra21.backend.spring.projeto.model.Personagem;
import br.com.entra21.backend.spring.projeto.repository.IPersonagemRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/personagem")
public class PersonagemController {
	private final String PATH = "localhost:8080/personagem";
	@Autowired
	private IPersonagemRepository personagemRepository;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<Personagem> listAll() {
		List<Personagem> response = personagemRepository.findAll();
		response.forEach(programador -> {
			setMaturidadeNivel3(programador);
		});
		return response;

	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Personagem> get(@PathVariable("id") int id) {
		List<Personagem> response = personagemRepository.findById(id).stream().toList();
		response.forEach(programador -> {
			setMaturidadeNivel3(programador);
		});
		return response;

	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Personagem create(@RequestBody Personagem novoPersonagem) {
		Personagem response = personagemRepository.save(novoPersonagem);
		return response;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Optional<Personagem> update(@PathVariable("id") int id,
			@RequestBody Personagem personagemEditado) {
		Personagem atual = personagemRepository.findById(id).get();
		atual.setNome_heroi(personagemEditado.getNome_heroi());
		atual.setNome_real(personagemEditado.getNome_real());
		atual.setIdade(personagemEditado.getIdade());
		atual.setAcessorio(personagemEditado.getAcessorio());
		atual.setHabilidade(personagemEditado.getHabilidade());
		personagemRepository.save(atual);
		return personagemRepository.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean delete(@PathVariable("id") int id) {
		personagemRepository.deleteById(id);
		return !personagemRepository.existsById(id);
	}

	private void setMaturidadeNivel3(Personagem personagem) {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Accept : application/json");
		headers.add("Content-type : application/json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			personagem.setLinks(null);
			String nomeAtual = personagem.getNome_heroi();
			personagem.setNome_heroi("Nome diferente");
			String jsonUpdate = mapper.writeValueAsString(personagem);
			personagem.setNome_heroi(nomeAtual);
			personagem.setId(null);
			String jsonCreate = mapper.writeValueAsString(personagem);
			personagem.setLinks(new ArrayList<>());
			personagem.getLinks().add(new ItemNivel3("GET", PATH, null, null));
			personagem.getLinks().add(new ItemNivel3("GET", PATH + "/" + personagem.getId(), null, null));
			personagem.getLinks().add(new ItemNivel3("POST", PATH, headers, jsonCreate));
			personagem.getLinks().add(new ItemNivel3("PUT", PATH + "/" + personagem.getId(), headers, jsonUpdate));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/habilidade")
	public Personagem habilidade(@RequestBody Personagem credencial) {

		return personagemRepository.habilidade(credencial.getHabilidade());

	}

	@PostMapping("/nome_heroi")
	public Personagem nome_heroi(@RequestBody Personagem credencial) {

		return personagemRepository.nome_heroi(credencial.getNome_heroi());

	}

	@GetMapping("/maior_que/{idade}")
	public List<Personagem> maiorQue(@PathVariable("idade") Integer idade) {

		return personagemRepository.maiorIdade(idade);

	}

	@PostMapping("/nome_real")
	public Personagem nome_real(@RequestBody Personagem credencial) {

		return personagemRepository.nome_real(credencial.getNome_real());

	}

	@PostMapping("/ficha")
	public Personagem ficha(@RequestBody Personagem credencial) {

		return personagemRepository.ficha(credencial.getNome_heroi(), credencial.getAcessorio());

	}

	@PostMapping("/ficha_comp")
	public Personagem ficha_comp(@RequestBody Personagem credencial) {

		return personagemRepository.ficha_comp(credencial.getNome_heroi(), credencial.getAcessorio(),credencial.getHabilidade());

	}

}
