package br.com.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	List<Consulta> findByPacienteId(Long pacienteId);

}
