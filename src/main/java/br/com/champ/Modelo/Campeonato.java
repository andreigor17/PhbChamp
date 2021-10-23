package br.com.champ.Modelo;

import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author andre
 */
@Entity
public class Campeonato extends ModeloGenerico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Team> Teams;
    private String nome;
    private int partidas;
    private int roundsGanhos;
    private int roundsPerdidos;
    private int roundsSaldo;
    private int pontos;
    private String record;

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

    public int getPartidas() {
        return partidas;
    }

    public void setPartidas(int partidas) {
        this.partidas = partidas;
    }

    public int getRoundsGanhos() {
        return roundsGanhos;
    }

    public void setRoundsGanhos(int roundsGanhos) {
        this.roundsGanhos = roundsGanhos;
    }

    public int getRoundsPerdidos() {
        return roundsPerdidos;
    }

    public void setRoundsPerdidos(int roundsPerdidos) {
        this.roundsPerdidos = roundsPerdidos;
    }

    public int getRoundsSaldo() {
        return roundsSaldo;
    }

    public void setRoundsSaldo(int roundsSaldo) {
        this.roundsSaldo = roundsSaldo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

        
}
