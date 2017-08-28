package br.com.verity.pause.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.verity.pause.bean.ApontamentosBean;
import br.com.verity.pause.bean.FuncionarioBean;
import br.com.verity.pause.business.ImportacaoBusiness;
import br.com.verity.pause.exception.BusinessException;

@Controller
@RequestMapping(value = "/importacao")
public class ImportacaoController {

	@Autowired
	private ImportacaoBusiness importacaoBusiness;
	
	@Autowired
	private List<FuncionarioBean> funcionariosImportacao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setAutoGrowCollectionLimit(2048);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String acessar() {
		return "importacao/importacao";
	}

	@ResponseBody
	@RequestMapping(value = "importar-arquivo/{empresa}", method = RequestMethod.POST)
	public List<FuncionarioBean> importarArquivo(@PathVariable String empresa, MultipartHttpServletRequest request,
			Model model) {
		List<MultipartFile> arquivo = request.getFiles("file");
		funcionariosImportacao = new ArrayList<FuncionarioBean>();
		FuncionarioBean funcionario = new FuncionarioBean();
		try {
			String caminho = this.salvarTxt(arquivo);
			funcionariosImportacao = importacaoBusiness.importarTxt(caminho, empresa);
		} catch (BusinessException e) {
			funcionario.setMensagem("Arquivo contém mais de uma data.");
			funcionariosImportacao.add(funcionario);
			return funcionariosImportacao;
		}catch (IOException e) {
			funcionario.setMensagem("Não foi possível abrir o arquivo.");
			funcionariosImportacao.add(funcionario);
			return funcionariosImportacao;
		}
		return funcionariosImportacao;
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	@ResponseBody
	public String salvar(RedirectAttributes redirect) {
		List<ApontamentosBean> apontamentos = new ArrayList<ApontamentosBean>();
		
		for (FuncionarioBean funcionarioBean : funcionariosImportacao) {
			apontamentos.addAll(funcionarioBean.getApontamentos());
		}
		
		importacaoBusiness.salvarApontamentos(apontamentos);
		
		return "redirect:/importacao";
	}

	public String salvarTxt(List<MultipartFile> multipartFiles) throws IOException {
		String arquivo = null;
		String directory = "C:/";
		File file = new File(directory);
		file.mkdirs();
		for (MultipartFile multipartFile : multipartFiles) {
			file = new File(directory + multipartFile.getOriginalFilename());
			IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
			arquivo = directory + multipartFile.getOriginalFilename();
		}
		return arquivo;
	}
}