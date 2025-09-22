package br.com.suporteSenai.suporte.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditarSolicitacaoController {

	@GetMapping("/editarSolicitacao")
	public String editarSolicitacao() {
		return "editarSolicitacao";
	}
}
