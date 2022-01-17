package br.com.champ.Modelo;

import br.com.champ.Enums.StatusCamp;
import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author andre
 */
@Entity
public class Campeonato extends ModeloGenerico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Team> Teams;
//    @ManyToMany
//    private List<Player> players;
    private String nome;
    @Enumerated(EnumType.STRING)
    private StatusCamp status;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Partida> partidas;

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public List<Player> getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(List<Player> players) {
//        this.players = players;
//    }

    public StatusCamp getStatus() {
        return status;
    }

    public void setStatus(StatusCamp status) {
        this.status = status;
    }

}
