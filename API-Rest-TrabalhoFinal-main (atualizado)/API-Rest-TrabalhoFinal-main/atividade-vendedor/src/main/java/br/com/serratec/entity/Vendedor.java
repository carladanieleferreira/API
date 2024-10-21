package br.com.serratec.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public abstract class Vendedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="Identificador único do usuário")
	private Long id;
	
	@NotBlank(message = "Preencha o nome")
	@Size(max = 50)
	@Column(nullable = false, length = 50)
	@Schema(description="Nome do usuário")
	private String nome;
	
	@Email
	@Schema(description="Email único do usuário")
	private String email;
	
	public class SalarioConstants {
		public static final long SALARIO_MINIMO = 1412;
	}
	
	@NotBlank(message = "Preencha o valor do salário")
	@Min(value = SalarioConstants.SALARIO_MINIMO,
	message = "O salário não pode ser menor que o salário mínimo")
	@Column(nullable = false, length = 50)
	@Schema(description="Salário do usuário")
	private Double salario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getnome() {
		return nome;
	}
	public void setnome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
}
