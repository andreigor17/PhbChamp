/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Utilitario;

import br.com.champ.Manager.ManagerPartida;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.ItemPartida;
import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class PartidaUtils {
    
     public static List<ItemPartida> gerarPartidas(Partida p, Campeonato camp, Team time1, Team time2, int qtdItensPartidas ) {

        int i = 0;
        List<ItemPartida> partidasGeradas = new ArrayList<>();
        try {
            for (i = 1; i <= qtdItensPartidas; i++) {
                ItemPartida newItem = new ItemPartida();
                if (camp != null) {
                    newItem.setCamp(camp);
                }

                newItem.setTeam1(time1);
                newItem.setTeam2(time2);
                partidasGeradas.add(newItem);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return partidasGeradas;
    }
    
}
