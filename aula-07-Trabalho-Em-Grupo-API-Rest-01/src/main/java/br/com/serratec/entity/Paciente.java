package br.com.serratec.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Paciente {
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Preenchimento obrigatório")
	@Size(max = 60, message = "Valor máximo de caracteres excedido")
	private String nome;

	@Email(message = "Email não válido")
	private String email;

	@NotBlank(message = "Preenchimento obrigatório")
	@Size(max = 15, min = 10)
	@Pattern(regexp="\\d{2} (\\d{5}|\\d{4})-\\d{4}")
	private String telefone;

//	//Foreign Keys
    @JsonBackReference
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private Set<Consulta> consultas;

	// Getters Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

}