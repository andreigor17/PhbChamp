package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import br.com.champ.Modelo.Player;
import javax.ejb.Stateless;
import br.com.champ.Modelo.Team;
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
import java.util.List;
import javax.persistence.Query;
import org.json.JSONException;

/**
 *
 * @author andre
 */
@Stateless
public class TeamServico {

    public List<Team> pesquisar(Team team) throws Exception {

        try {
            String url = "http://localhost:8090/teams";
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
            List<Team> t = new ArrayList<>();

            //Team[] userArray = gson.fromJson(response.toString(), Team[].class);
            Type userListType = new TypeToken<ArrayList<Team>>() {
            }.getType();

            ArrayList<Team> userArray = gson.fromJson(response.toString(), userListType);

            for (Team time : userArray) {
                t.add(time);
            }

            return t;
        } catch (IOException iOException) {
            System.err.println(iOException);
        } catch (JSONException jSONException) {
            System.err.println(jSONException);
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException);
        }
        return null;

    }

    public List<Team> autoCompleteTime() throws Exception {
        return buscaTimes();
    }

    private List<Team> buscaTimes() throws Exception {

        try {
            String url = "http://localhost:8090/teams";
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
            List<Team> t = new ArrayList<>();

            //Team[] userArray = gson.fromJson(response.toString(), Team[].class);
            Type userListType = new TypeToken<ArrayList<Team>>() {
            }.getType();

            ArrayList<Team> userArray = gson.fromJson(response.toString(), userListType);

            for (Team time : userArray) {
                t.add(time);
            }

            return t;
        } catch (IOException iOException) {
            System.err.println(iOException);
        } catch (JSONException jSONException) {
            System.err.println(jSONException);
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException);
        }
        return null;

    }

    public Team buscaTeam(Long id) {
        try {
            String url = "http://localhost:8090/teams/" + id;
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
            Team t1 = new Team();

            Team userArray = gson.fromJson(response.toString(), Team.class);

            t1 = userArray;
            return t1;
        } catch (IOException iOException) {
            System.err.println(iOException);
        } catch (JSONException jSONException) {
            System.err.println(jSONException);
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException);
        }
        return null;

    }

    public Team save(Team team) throws Exception {
        String url = "http://localhost:8090/teams";

        try {
            // Cria um objeto HttpURLConnection:
            HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

            try {
                // Define que a conexão pode enviar informações e obtê-las de volta:
                request.setDoOutput(true);
                request.setDoInput(true);

                // Define o content-type:
                request.setRequestProperty("Content-Type", "application/json");

                // Define o método da requisição:
                request.setRequestMethod("POST");

                // Conecta na URL:
                request.connect();
                // Montando o  Json
                Gson gson = new Gson();
                String json = gson.toJson(team);
                System.out.println("Montagem do time: " + json);

                // Escreve o objeto JSON usando o OutputStream da requisição:
                try (OutputStream outputStream = request.getOutputStream()) {
                    outputStream.write(json.getBytes("UTF-8"));
                }

                Team t = new Team();

                Team userArray = gson.fromJson(readResponse(request), Team.class);
                t = userArray;

                return t;
                // Caso você queira usar o código HTTP para fazer alguma coisa, descomente esta linha.
                //int response = request.getResponseCode();

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

}
