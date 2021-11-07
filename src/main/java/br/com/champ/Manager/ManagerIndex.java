package br.com.champ.Manager;

import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.CampeonatoServico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerIndex implements Serializable{
    @EJB
    CampeonatoServico campServico;
    
    private Campeonato camp;
    private List<Campeonato> campsAtuais;
    private List<Team> times;
    
    @PostConstruct
    public void init() {
        instanciar();        
            
    }
    
    public void instanciar(){
        this.camp = new Campeonato();
        this.campsAtuais = new ArrayList<>();
        this.times = new ArrayList<>();
    }

    public Campeonato getCamp() {
        return camp;
    }

    public void setCamp(Campeonato camp) {
        this.camp = camp;
    }

    public List<Campeonato> getCampsAtuais() {
        return campsAtuais;
    }

    public void setCampsAtuais(List<Campeonato> campsAtuais) {
        this.campsAtuais = campsAtuais;
    }

    public List<Team> getTimes() {
        return times;
    }

    public void setTimes(List<Team> times) {
        this.times = times;
    }
    
    
    public List<Campeonato> autoCompletarCamps() {
        return campServico.autoCompleteCamps();
    }
    
    public void mostrarCampeonato(){
        this.camp = campServico.find(this.camp.getId());
        
    }
    
}
