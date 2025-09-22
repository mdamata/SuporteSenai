package br.com.suporteSenai.suporte.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.suporteSenai.suporte.model.Solicitacao;
import br.com.suporteSenai.suporte.repository.SolicitacaoRepository;

@Controller
public class solicitacaoController {
	
	@Autowired
	private SolicitacaoRepository sr;
	
	
	@GetMapping(value="/solicitacao")
	public String solicitacao() {
		return "solicitacao";
	}
	
	@PostMapping(value="/solicitacao")
		public String solicitacao(Solicitacao solicitacao) {
		sr.save(solicitacao);	
		return "redirect:/solicitacao";
		
	}
	
	@RequestMapping("/solicitacoes")
	public ModelAndView listasolicitacao() {
		ModelAndView mv = new ModelAndView("painelTecnico");
		Iterable<Solicitacao> solicitacao = sr.findAll();
		mv.addObject("solicitacao", solicitacao);
		return mv;
	}
	
	@GetMapping("/editarSolicitacao/{id}")
	public String editarSolicitacao(@PathVariable("id") long id, Model model) {
		Solicitacao solicitacao = sr.findById(id).orElse(null);
		if (solicitacao != null) {
			model.addAttribute("solicitacao", solicitacao);
			return "editarSolicitacao";
		}
		return "redirect:/painelTecnico";
		
	}
	@PostMapping("/atualizarSolicitacao{id}")
	public String atualizarsolicitacao(@PathVariable("id") long id, Solicitacao solicitacaoAtualizada) {
		return atualizarSolicitacao(id, solicitacaoAtualizada);
	
	}
	@PutMapping("/atualizarSolicitacao/{id}")
	public String atualizarSolicitacao(@PathVariable("id") long id, Solicitacao solicitacaoAtualizada) {
		Solicitacao solicitacaoExistente = sr.findById(id).orElse(null);
		
		if (solicitacaoExistente != null) {
			solicitacaoExistente.setNif(solicitacaoAtualizada.getNif());
			solicitacaoExistente.setNomeSolicitante(solicitacaoAtualizada.getNomeSolicitante());
			solicitacaoExistente.setNumeroSala(solicitacaoAtualizada.getNumeroSala());
			solicitacaoExistente.setCodigoPatrimonio(solicitacaoAtualizada.getCodigoPatrimonio());
			solicitacaoExistente.setDescricaoProblema(solicitacaoAtualizada.getDescricaoProblema());
			solicitacaoExistente.setTipoProblema(solicitacaoAtualizada.getTipoProblema());
			solicitacaoExistente.setStatus(solicitacaoAtualizada.getStatus());
			
			sr.save(solicitacaoExistente);
		}
		return "redirect:/painelTecnico";
	}
	
	@DeleteMapping("/excluirSolicitacao/{id}")
	public String excluirSolicitacao(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		try {
			Solicitacao solicitacao = sr.findById(id).orElse(null);
			if (solicitacao != null) {
				sr.delete(solicitacao);
				redirectAttributes.addFlashAttribute("mensagem", "Solicitacao excluida com sucesso!");
			} else {
				redirectAttributes.addFlashAttribute("erro", "Solicitacao não encontrada!");
			}
		}
		catch (Exception e)	{
			redirectAttributes.addFlashAttribute("erro", "Erro ao excluir solicitacao: " + e.getMessage());
		}
		return "redirect:/painelTecnico";
		
	}
	@PostMapping("/excluirSolicitacao/{id}")
	public String excluirSolicitacaoPost(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		return excluirSolicitacao(id, redirectAttributes);
	}
}
