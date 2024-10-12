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
import br.com.serratec.exception.CamposInvalidosException;
import br.com.serratec.repository.ConsultaRepository;
import br.com.serratec.repository.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private ConsultaRepository consultaRepository;	
	
	@Autowired
	private PacienteRepository repository;
	
	//INSERIR UM PACIENTE
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente inserirPaciente(@Valid @RequestBody Paciente paciente) {
		if(true) {
			return repository.save(paciente);
		}
		else {
		throw new CamposInvalidosException("Teste01", "Erroteste01");
		}
	}
	
	//ATUALIZAR UM PACIENTE
	@PutMapping("{id}")
	public ResponseEntity<Paciente> alterarPaciente(@PathVariable Long id, @Valid @RequestBody Paciente p) {
		if (repository.existsById(id)) {
			p.setId(id); //aqui ele vai fazer o put se não ele iria criar outro novo
			return ResponseEntity.ok(repository.save(p));
		}
		return ResponseEntity.notFound().build();
	}
	
	//APAGAR UM PACIENTE
	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagarPaciente(@PathVariable Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	//LISTAR UM PACIENTE E SUAS CONSULTAS
    @GetMapping("/{pacienteId}/consultas")
    public List<Consulta> getConsultasByPacienteId(@PathVariable Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }
	
	@GetMapping("{id}")
	public ResponseEntity<Paciente> exibirItem(@PathVariable Long id) {
		Optional<Paciente> pacienteOptional = repository.findById(id);
		if (pacienteOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pacienteOptional.get());
	}
}
