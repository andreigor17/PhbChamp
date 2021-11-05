/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Modelo;

import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 *
 * @author gustavo
 */

@Entity
public class Partida extends ModeloGenerico implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Campeonato camp;
    
    @ManyToOne
    private Team team1;
    
    @ManyToOne
    private Team team2;
    
    private int scoreT1;
    
    private int scoreT2;

    public Partida() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Partida(Campeonato camp, Team team1, Team team2) {
        this.camp = camp;
        this.team1 = team1;
        this.team2 = team2;
    }
    
    
    
}
