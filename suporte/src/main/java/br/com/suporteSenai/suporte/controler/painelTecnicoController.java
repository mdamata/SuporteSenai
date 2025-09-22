package br.com.suporteSenai.suporte.controler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.suporteSenai.suporte.model.Paineltecnico;
import br.com.suporteSenai.suporte.model.Solicitacao;
import br.com.suporteSenai.suporte.repository.PainelTecnicoRepository;
import br.com.suporteSenai.suporte.repository.SolicitacaoRepository;

@Controller
public class painelTecnicoController {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	
	@Autowired
	private PainelTecnicoRepository painelTecnicoRepository;
	
	/**
	 * exibe o painel com todas as solicitações
	 * 
	 */
	
	@GetMapping("/painelTecnico")
	public String mostrarPainelTecnico(Model model) {
		Iterable<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
		model.addAttribute("solicitacoes", solicitacoes);
		
		return "painelTecnico";
		
		
	}
	@GetMapping("/assumirSolicitacao/{id}")
	public String assumirsolicitacao(@PathVariable("id") long id, Model model) {
		Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
		if (solicitacao.isPresent()) {
			model.addAttribute("solicitacao", solicitacao.get());
			return "formTecnico";
			
		}
		return "redirect:/painelTecnico";
	}
	@PostMapping("/assumirSolicitacao")
	public String processarAssumirsolicitacao(
			@RequestParam Long idSolicitacao,
			@RequestParam String tecnicoResponsavel,
			@RequestParam String observacoes) {
	
		Paineltecnico painel = new Paineltecnico();
		painel.setIdSolicitacao(idSolicitacao);
		painel.setTecnicoResponsavel(tecnicoResponsavel);
		painel.setObservacoes(observacoes);
		painel.setStatus("Em Andamento");
		painel.setAcao("Assumida");
		
		painelTecnicoRepository.save(painel);
		
		
		Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(idSolicitacao);
		if (solicitacao.isPresent()) {
			Solicitacao sol = solicitacao.get();
			sol.setStatus("Em Andamento");
			solicitacaoRepository.save(sol);
		}
		return "redirect:/painelTecnico";
		
	}
	
	
	@GetMapping("/concluirSolicitacao/{id}")
	public String concluirSolicitacao(@PathVariable("id") long id) {
		Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
		if (solicitacao.isPresent()) {
			Solicitacao sol = solicitacao.get();
			sol.setStatus("Concluido");
			solicitacaoRepository.save(sol);
			
			//Atualiza também no painel tecnico existente
			Optional<Paineltecnico> painelOpt = painelTecnicoRepository.findByIdSolicitacao(id);
			if (painelOpt.isPresent()) {
				Paineltecnico painel = painelOpt.get();
				painel.setStatus("Concluido");
				painel.setAcao("Concluida");
				painelTecnicoRepository.save(painel);
				
			}
			
		}
		return "redirect:/painelTecnico";
	}

		//Método para concluir solicitacao
		@PostMapping("/concluirSolicitacao/{id}")
		public String concluirSolicitacao(@PathVariable Long id, RedirectAttributes redirectAttributes) {
			Solicitacao solicitacao = solicitacaoRepository.findById(id).orElse(null);
			if (solicitacao != null && "Em andamento".equals(solicitacao.getStatus())) {
				solicitacao.setStatus("Cocluido");
				redirectAttributes.addFlashAttribute("Mensagem","Solicitação concluida com sucesso");
			} else {
				redirectAttributes.addFlashAttribute("erro","Solicitação não encontrada ou não  pode ser concluida");
			}
		    return"redirect:/painelTecnico";
				
	}
		
		@PostMapping("/assumirSolicitacao/{id}")
		public String assumirSolicitacao (@PathVariable Long id, RedirectAttributes redirectAttributes) {
			Solicitacao solicitacao = solicitacaoRepository.findById(id).orElse(null);
			if (solicitacao != null && "Pendende".equals(solicitacao.getStatus())) {
				solicitacao.setStatus("Em andamento");
				redirectAttributes.addFlashAttribute("mensagem", "Solicitação assumida com sucesso");
			}else {
				redirectAttributes.addFlashAttribute("erro","Solicitação não encontrada ou já assumida. ");
				
			}
			return "redirect:/painelTecnico";
		}
}





