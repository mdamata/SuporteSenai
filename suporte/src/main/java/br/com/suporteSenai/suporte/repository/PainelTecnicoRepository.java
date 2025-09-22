package br.com.suporteSenai.suporte.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.suporteSenai.suporte.model.Paineltecnico;
	


@Repository
public interface PainelTecnicoRepository extends CrudRepository<Paineltecnico,Long>{
 
	Optional<Paineltecnico> findByIdSolicitacao(Long idSolicitacao);
	
	
}
