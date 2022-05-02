/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Modelo;

import br.com.champ.Enums.Maps;
import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;

/**
 *
 * @author gustavo
 */
public class Partida extends ModeloGenerico implements Serializable {

    private Team team1;

    private Team team2;

    private Campeonato camp;

    private int scoreT1;

    private int scoreT2;

    private Maps mapas;

    public Partida() {
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getScoreT1() {
        return scoreT1;
    }

    public void setScoreT1(int scoreT1) {
        this.scoreT1 = scoreT1;
    }

    public int getScoreT2() {
        return scoreT2;
    }

    public void setScoreT2(int scoreT2) {
        this.scoreT2 = scoreT2;
    }

    public Maps getMapas() {
        return mapas;
    }

    public void setMapas(Maps mapas) {
        this.mapas = mapas;
    }

    public Campeonato getCamp() {
        return camp;
    }

    public void setCamp(Campeonato camp) {
        this.camp = camp;
    }        
    
    public Partida(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        
    }

    @Override
    public String toString() {
        return "Partida{" + "team1=" + team1 + ", team2=" + team2 + ", camp=" + camp + ", scoreT1=" + scoreT1 + ", scoreT2=" + scoreT2 + ", mapas=" + mapas + '}';
    }
    
    

}
