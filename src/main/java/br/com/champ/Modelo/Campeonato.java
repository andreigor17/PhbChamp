package br.com.champ.Modelo;

import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author andre
 */
@Entity
public class Campeonato extends ModeloGenerico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Team> Teams;
    private String nome;

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
        
}
