package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum StatusConsulta {
	//Enums
	AGUARDANDO(1, "Aguardando"),
	EM_ATENDIMENTO(2, "Em atendimento"),
	ATENDIDO(3, "Atendido");
	//Atributos
	private Integer codigo;
	private String tipo;
	//Construtor para organização
	private StatusConsulta(Integer codigo, String tipo) {
		this.codigo = codigo;
		this.tipo = tipo;
	}
	//Getters Setters
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	//Exceptions
	@JsonCreator
	public static StatusConsulta verificar(Integer valor) {
		for (StatusConsulta c: StatusConsulta.values()) {
			if (c.getCodigo().equals(valor))
				return c;
		}
		throw new EnumException("Termo inválido");
	}
}
