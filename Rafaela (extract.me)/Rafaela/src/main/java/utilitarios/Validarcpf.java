package utilitarios;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validarcpf {
	public static Endereco buscarCep(String cep) throws IllegalArgumentException, IllegalAccessException {
		String json;
		try {
			URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			StringBuilder jsonSb = new StringBuilder();
			br.lines().forEach(l -> jsonSb.append(l.trim()));
			json = jsonSb.toString();
			return formataCEP (json);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//System.out.println(json);
	//Esta parte do código foi desmembrada em 2 operações
	//a versão 2.0 terá esta melhoria

	public static Endereco formataCEP(String cep) throws IllegalArgumentException, IllegalAccessException{

		Map<String,String> mapa = new HashMap<>();

		Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(cep);
		while (matcher.find()) {
			String[] group = matcher.group().split(":");
			//		            System.out.println("Grupo 0 " + group[0]);
			//		            System.out.println( "Grupo 1 " + group[1]);
			mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());		       
		}
		System.out.println(mapa);
		Endereco end = new Endereco();
		Class<?> classe = end.getClass();
		Field fieldlist[] = classe.getDeclaredFields();
		String valor;
		for (int i=0; i < fieldlist.length; i++) {
			if (mapa.containsKey(fieldlist[i].getName())){
				valor = mapa.get(fieldlist[i].getName()).toString();
				//a variável valor pode ser descartada
				fieldlist[i].setAccessible(true);
				fieldlist[i].set(end, valor);
				//System.out.println(fieldlist[i].getName() + " , " + valor);
			}
		}
		return end;
	} //fim do método formataCEP
} //fim da classe

