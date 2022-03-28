package br.com.champ.Modelo;

import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;

/**
 *
 * @author andre
 */
public class Estatisticas extends ModeloGenerico implements Serializable {
    
    private Integer kills = 0;
    private Integer deaths = 0;
    private Integer assists = 0;
    private Integer roundsGanhos = 0;
    private Integer roundsPerdidos = 0;
    private Integer partidas = 0;
    private Integer pontos = 0;
    private Team team;
    private Campeonato campeonato;

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getRoundsGanhos() {
        return roundsGanhos;
    }

    public void setRoundsGanhos(Integer roundsGanhos) {
        this.roundsGanhos = roundsGanhos;
    }

    public Integer getRoundsPerdidos() {
        return roundsPerdidos;
    }

    public void setRoundsPerdidos(Integer roundsPerdidos) {
        this.roundsPerdidos = roundsPerdidos;
    }

    public Integer getPartidas() {
        return partidas;
    }

    public void setPartidas(Integer partidas) {
        this.partidas = partidas;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

}
