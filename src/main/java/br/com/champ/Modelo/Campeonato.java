package br.com.champ.Modelo;

import br.com.champ.Enums.StatusCamp;
import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author andre
 */
public class Campeonato extends ModeloGenerico implements Serializable {

    private String nome;
    private List<Team> teams;
    private StatusCamp status;
    private List<Partida> partidas;
    private List<Estatisticas> estatisticas;        
    private String dataCamp;
    private String dataString;

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusCamp getStatus() {
        return status;
    }

    public void setStatus(StatusCamp status) {
        this.status = status;
    }

    public List<Estatisticas> getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(List<Estatisticas> estatisticas) {
        this.estatisticas = estatisticas;
    }

    public String getDataCamp() {
        return dataCamp;
    }

    public void setDataCamp(String dataCamp) {
        this.dataCamp = dataCamp;
    }


    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

}
