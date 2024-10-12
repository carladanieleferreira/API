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

import br.com.serratec.entity.Medico;
import br.com.serratec.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	@Autowired
	private MedicoRepository repository;
	
	//INSERIR UM MEDICO
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Medico inserirMedico(@Valid @RequestBody Medico medico) {
		return repository.save(medico);
	}

	//LISTAR TODOS MEDICOS
	@GetMapping
	public List<Medico> listarTodos() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Medico> exibirItem(@PathVariable Long id) {
		Optional<Medico> medicoOptional = repository.findById(id);
		if (medicoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(medicoOptional.get());
	}
	
	//ATUALIZAR MEDICO
	@PutMapping("{id}")
	public ResponseEntity<Medico> alterarMedico(@PathVariable Long id, @Valid @RequestBody Medico m) {
		if (repository.existsById(id)) {
			m.setId(id); //aqui ele vai fazer o put se não ele iria criar outro novo
			return ResponseEntity.ok(repository.save(m));
		}
		return ResponseEntity.notFound().build();
	}
	
	//DELETAR MEDICO
	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagarMedico(@PathVariable Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
