package br.com.champ.Servico;

import javax.ejb.Stateless;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Configuracao;
import br.com.champ.Modelo.Player;
import br.com.champ.Utilitario.APIPath;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
import java.util.List;
import javax.ejb.EJB;
import org.json.JSONException;

@Stateless
public class CampeonatoServico {

    @EJB
    private ConfiguracaoServico configuracaoServico;

    public Configuracao obterConfiguracao() {
        return configuracaoServico.buscaConfig();

    }
    
    public String pathToAPI() throws IOException {
        return APIPath.pathToAPI();

    }

    public List<Campeonato> pesquisar(Campeonato camp) throws Exception {
        try {
            String url = pathToAPI() + "/campeonatos";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
            Gson gson = new Gson();
            List<Campeonato> c = new ArrayList<>();

            //Team[] userArray = gson.fromJson(response.toString(), Team[].class);
            Type userListType = new TypeToken<ArrayList<Campeonato>>() {
            }.getType();

            ArrayList<Campeonato> userArray = gson.fromJson(response.toString(), userListType);

            for (Campeonato campeonato : userArray) {
                c.add(campeonato);
            }

            return c;
        } catch (IOException iOException) {
            System.err.println(iOException);
        } catch (JSONException jSONException) {
            System.err.println(jSONException);
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException);
        }
        return null;

    }

    public List<Campeonato> buscaCampPorPlayer(Player player) {
        return null;

    }

    public List<Campeonato> autoCompleteCamps() {
        return pesquisarCampsAtuais();
    }

    public List<Campeonato> pesquisarCampsAtuais() {
        try {
            String url = pathToAPI() + "/campeonatos";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
            Gson gson = new Gson();
            List<Campeonato> c = new ArrayList<>();

            //Team[] userArray = gson.fromJson(response.toString(), Team[].class);
            Type userListType = new TypeToken<ArrayList<Campeonato>>() {
            }.getType();

            ArrayList<Campeonato> userArray = gson.fromJson(response.toString(), userListType);

            for (Campeonato campeonato : userArray) {
                c.add(campeonato);
            }

            return c;
        } catch (IOException iOException) {
            System.err.println(iOException);
        } catch (JSONException jSONException) {
            System.err.println(jSONException);
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException);
        }
        return null;

    }

    public Campeonato save(Campeonato camp, Long id, String uri) throws Exception {

        String url;
        if (id != null) {
            url = pathToAPI() + uri + id;
        } else {
            url = pathToAPI() + uri;
        }

        try {
            // Cria um objeto HttpURLConnection:
            HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

            try {
                // Define que a conex??o pode enviar informa????es e obt??-las de volta:
                request.setDoOutput(true);
                request.setDoInput(true);

                // Define o content-type:
                request.setRequestProperty("Content-Type", "application/json");

                // Define o m??todo da requisi????o:
                if (id != null) {
                    request.setRequestMethod("PUT");
                } else {
                    request.setRequestMethod("POST");
                }

                // Conecta na URL:
                request.connect();
                // Montando o  Json
                Gson gson = new Gson();
                String json = gson.toJson(camp);
                System.out.println("Montagem do campeonato: " + json);

                // Escreve o objeto JSON usando o OutputStream da requisi????o:
                try (OutputStream outputStream = request.getOutputStream()) {
                    outputStream.write(json.getBytes("UTF-8"));
                }

                // Caso voc?? queira usar o c??digo HTTP para fazer alguma coisa, descomente esta linha.
                //int response = request.getResponseCode();            
                Campeonato c = new Campeonato();

                Campeonato userArray = gson.fromJson(readResponse(request), Campeonato.class);
                c = userArray;

                return c;
            } finally {
                request.disconnect();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
    }

    private String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
        return new String(os.toByteArray());
    }

    public Campeonato buscaCamp(Long id) {
        try {
            String url = pathToAPI() + "/campeonatos/" + id;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
            Gson gson = new Gson();
            Campeonato c = new Campeonato();

            Campeonato userArray = gson.fromJson(response.toString(), Campeonato.class);

            c = userArray;
            return c;
        } catch (IOException iOException) {
            System.err.println(iOException);
        } catch (JSONException jSONException) {
            System.err.println(jSONException);
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException);
        }
        return null;

    }

}
