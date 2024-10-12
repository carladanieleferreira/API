package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Consulta;
import br.com.serratec.entity.Paciente;
import br.com.serratec.repository.ConsultaRepository;
import br.com.serratec.repository.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	@Autowired
	private ConsultaRepository repository;

	@Autowired
	private PacienteRepository pacienteRepository;
	//INSERE CONSULTA
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Consulta inserirConsulta(@Valid @RequestBody Consulta consulta) {
		return repository.save(consulta);
	}
	
	//LISTA TODAS CONSULTAS DEBUG
	@GetMapping
	public List<Consulta> listarTodos() {
		return repository.findAll();
	}	
	@GetMapping("{id}")
	public ResponseEntity<Consulta> exibirItem(@PathVariable Long id) {
		Optional<Consulta> consultaOptional = repository.findById(id);
		if (consultaOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consultaOptional.get());
	}

	//ALTERA CONSULTA
	@PutMapping("{id}")
	public ResponseEntity<Consulta> alterarConsulta(@PathVariable Long id, @Valid @RequestBody Consulta c) {
		if (repository.existsById(id)) {
			c.setId(id); //aqui ele vai fazer o put se não ele iria criar outro novo
			return ResponseEntity.ok(repository.save(c));
		}
		return ResponseEntity.notFound().build();
	}

	//DELETA CONSULTA
	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagarConsulta(@PathVariable Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}	
}
