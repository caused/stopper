package br.com.prosperity.integration;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.FileCopyUtils;

import br.com.verity.regponto.bean.UsuarioBean;

public class SavIntegration {

	// public List<ProjetoBean> getListProjetos() {
	// List<ProjetoBean> projetos = new ArrayList<ProjetoBean>();
	//
	// Properties props = this.getProp();
	// String endereco = props.getProperty("prop.integration.listprojeto");
	// try {
	// URL url = new URL(endereco);
	// String response = genericGet(url);
	//
	// JSONArray objetos = new JSONArray(response);
	//
	// for (int i = 0; i < objetos.length(); i++) {
	// ProjetoBean projeto = new ProjetoBean();
	// JSONObject objeto = objetos.getJSONObject(i);
	// projeto.setId(objeto.getInt("id"));
	// projeto.setNome(objeto.getString("nome"));
	// projetos.add(projeto);
	// }
	// } catch (MalformedURLException | JSONException e) {
	// e.printStackTrace();
	// }
	//
	// return projetos;
	// }

	public UsuarioBean getUsuario(String user) {
		UsuarioBean usuario = new UsuarioBean();
		ObjectMapper mapper = new ObjectMapper();
		// Properties props = this.getProp();
		String endereco = "http://localhost:9090/sav/getUser/";
		try {
			URL url = new URL(endereco + user);
			usuario = mapper.readValue(url, UsuarioBean.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public static String genericGet(URL url) {
		InputStream inputstream = null;
		String response = null;
		try {
			// Criando objeto para efetuar conexão com o serviço
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// Atribuindo método GET para conexão
			conn.setRequestMethod("GET");

			// Atribuindo JSON como parâmetro de retorno
			conn.setRequestProperty("Accept", "application/json");

			// Verificando se a conexão com o serviço foi bem sucedida
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			// pegando resultado do serviço
			inputstream = conn.getInputStream();

			// Transformando em Stting
			response = new String(FileCopyUtils.copyToByteArray(inputstream), StandardCharsets.UTF_8);

			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Properties getProp() {
		try {
			Properties props = new Properties();
			InputStream file = getClass().getResourceAsStream(".regponto-business/src/main/resources/dados.properties");
			props.load(file);
			return props;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}