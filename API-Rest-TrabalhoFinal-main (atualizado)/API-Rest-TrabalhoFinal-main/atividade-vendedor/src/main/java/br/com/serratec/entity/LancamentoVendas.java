package br.com.serratec.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class LancamentoVendas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description="Identificador único da venda")
	private Long id;
	
	@NotBlank(message = "Preencha a data da venda AAAA-MM-DD")
	@Column(nullable = false, length = 50)
	@Schema(description="Nome do usuário")
	private LocalDate data; //2024-10-12 21:57
	
	@Email
	@Schema(description="Email único do usuário")
	private Double valor;
	
	@NotBlank(message = "Preencha o valor do salário")
	@Column(nullable = false, length = 50)
	@Schema(description="Salário do usuário")
	private Double salario;
	
	//relação ORM com vendedor autonomo	n vendas para 1 vendedor
	
	//Foreign Key
	//LANÇAMENTOS DE VENDA DO VENDEDOR AUTONOMO
	@ManyToOne //relação n para 1
	@JsonBackReference //complementa o @JsonManagedReference para não dar loops
	@JoinColumn(name ="id_vendedorAutonomo")
	private VendedorAutonomo vendedorAutonomo;
	
	public VendedorAutonomo getVeiculo() {
		return vendedorAutonomo;
	}
	public void setVeiculo(VendedorAutonomo vendedorAutonomo) {
		this.vendedorAutonomo = vendedorAutonomo;
	}
}
