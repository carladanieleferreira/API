package br.com.serratec.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class VendedorAutonomo extends Vendedor{
	//pode dar problema se for 0,00?
	@Column(nullable = false, length = 50)
	@Schema(description="Salário do usuário")
	private Double comissao;
	
	//relação ORM com vendas 1 vendedor para n vendas
	
	// o join collumn está la, aqui é a regra do negocio,
	// então só usa o mappedBy apontando 
	//pro "private VendedorAutonomo vendedorAutonomo;" que
	//está lá em LancamentoVendas
	
	@JsonManagedReference //essa anotação evita loopings
	@OneToMany(mappedBy = "vendedorAutonomo") // relação 1 para n
	private List<LancamentoVendas> lancamentos = new ArrayList<>();

	public List<LancamentoVendas> getLancamentoVenda() {
		return lancamentos;
	}
}
