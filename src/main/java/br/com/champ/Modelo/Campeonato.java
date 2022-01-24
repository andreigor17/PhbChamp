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
    
    private List<Team> Teams;    
    private List<Player> players;
    private String nome;    
    private StatusCamp status;
    private List<Partida> partidas;
    

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public List<Team> getTeams() {
        return Teams;
    }

    public void setTeams(List<Team> Teams) {
        this.Teams = Teams;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public StatusCamp getStatus() {
        return status;
    }

    public void setStatus(StatusCamp status) {
        this.status = status;
    }

}
