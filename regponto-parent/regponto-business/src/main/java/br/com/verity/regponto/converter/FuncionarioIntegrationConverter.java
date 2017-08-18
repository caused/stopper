package br.com.verity.regponto.converter;

import org.springframework.stereotype.Component;

import br.com.verity.regponto.bean.FuncionarioBean;
import br.com.verity.regponto.bean.FuncionarioIntegrationBean;

@Component
public class FuncionarioIntegrationConverter implements Converter<FuncionarioIntegrationBean, FuncionarioBean>{

	@Override
	public FuncionarioIntegrationBean convertBeanToEntity(FuncionarioBean bean) {
		if (bean == null) {
			return null;
		}

		FuncionarioIntegrationBean entity = new FuncionarioIntegrationBean();
		
		entity.setId(bean.getId());
		entity.setNome(bean.getNome());
		entity.setPis(bean.getPis());
		
		return entity;
	}

	@Override
	public FuncionarioBean convertEntityToBean(FuncionarioIntegrationBean entity) {
		if (entity == null) {
			return null;
		}

		FuncionarioBean bean = new FuncionarioBean();
		
		bean.setId(entity.getId());
		bean.setNome(entity.getNome());
		bean.setPis(entity.getPis());
		
		return bean;
	}
}