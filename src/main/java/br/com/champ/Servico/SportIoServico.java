/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Servico;

import br.com.champ.Modelo.SportsIoMatches;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import org.json.JSONException;

/**
 *
 * @author andre
 */
@Stateless
public class SportIoServico {

        
        public List<SportsIoMatches> pesquisar() throws Exception {

        try {
            String url = "http://api.sportsdata.io/v3/csgo/scores/json/GamesByDate/2022-04-30?key=d4ec21c675ab4112a05279e8fd4be265";
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
            List<SportsIoMatches> p = new ArrayList<>();

            //Player[] userArray = gson.fromJson(response.toString(), Player[].class);
            Type userListType = new TypeToken<ArrayList<SportsIoMatches>>() {
            }.getType();

            ArrayList<SportsIoMatches> userArray = gson.fromJson(response.toString(), userListType);

            for (SportsIoMatches user : userArray) {
                p.add(user);
            }

            return p;
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
    

