/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Utilitario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author andre
 */
public class APIPath {

    public static String pathToAPI() {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("D:\\apipath.json")) {

            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) parser.parse(reader);
            } catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(APIPath.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(jsonObject);

            String caminho_api = (String) jsonObject.get("caminho_api");
            System.out.println(caminho_api);

            if (caminho_api != null) {
                return caminho_api;
            } else {
                return null;
            }

        } catch (IOException e) {
            System.err.println(e);
        }
        return null;

    }

    public static void writerPath(String path) {

        //Cria um Objeto JSON
        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        //Armazena dados em um Objeto JSON
        jsonObject.put("caminho_api", path);

        try {
            writeFile = new FileWriter("/opt/apipath.json");
            //Escreve no arquivo conteudo do Objeto JSON
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Imprimne na Tela o Objeto JSON para vizualização
        System.out.println(jsonObject);

    }

    public static void updateFile(String newPath) {

        String path = pathToAPI();
        String replace = path.replace(path, newPath);
        writerPath(replace);

    }

}
