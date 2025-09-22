package br.com.suporteSenai.suporte.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormTecnicoController {

	 @GetMapping("/formTecnico")
	 public String formTecnico() {
		 return"formTecnico";
	 }
}
