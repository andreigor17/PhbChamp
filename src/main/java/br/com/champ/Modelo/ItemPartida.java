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
 * @author andre
 */
public class ItemPartida extends ModeloGenerico implements Serializable {

    private int scoreT1;
    private int scoreT2;
    private Mapas mapas;
    private Long partida;
    private Team team1;
    private Team team2;
    private Long camp;
    private Estatisticas estatisticas;

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

    public Mapas getMapas() {
        return mapas;
    }

    public void setMapas(Mapas mapas) {
        this.mapas = mapas;
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
    
    public Estatisticas getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
    }   

    public Long getPartida() {
        return partida;
    }

    public void setPartida(Long partida) {
        this.partida = partida;
    }

    public Long getCamp() {
        return camp;
    }

    public void setCamp(Long camp) {
        this.camp = camp;
    }
    
}
