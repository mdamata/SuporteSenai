package br.com.suporteSenai.suporte.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.suporteSenai.suporte.model.Solicitacao;

@Repository
public interface SolicitacaoRepository extends CrudRepository<Solicitacao, Long>{

}
