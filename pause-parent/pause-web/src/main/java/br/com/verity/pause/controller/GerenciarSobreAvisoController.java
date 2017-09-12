package br.com.verity.pause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.verity.pause.bean.SobreAvisoBean;
import br.com.verity.pause.business.SobreAvisoBusiness;
import br.com.verity.pause.exception.BusinessException;

@RestController
@RequestMapping(value = "/sobre-aviso")
public class GerenciarSobreAvisoController {
	
	@Autowired
	private SobreAvisoBusiness sobreAvisoBusiness;
	
	@RequestMapping(value = "/inserir-sa", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody SobreAvisoBean sobreAviso){
		SobreAvisoBean sobreAvisoCriado = null;
		try {
			sobreAvisoCriado = sobreAvisoBusiness.salvar(sobreAviso);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(sobreAvisoCriado);
	}
	@RequestMapping(value = "/remover-sa", method = RequestMethod.DELETE)
	public ResponseEntity<String> remover(Integer id){
		sobreAvisoBusiness.remover(id);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK) ;
	}
}
