package br.com.champ.Manager;

import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Estatisticas;
import br.com.champ.Modelo.SportsIoMatches;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.CampeonatoServico;
import br.com.champ.Servico.EstatisticaServico;
import br.com.champ.Servico.SportIoServico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ManagerIndex implements Serializable {

    @EJB
    CampeonatoServico campServico;
    @EJB
    EstatisticaServico estatisticaServico;
    @EJB
    SportIoServico sportIoServico;

    private Campeonato camp;
    private List<Campeonato> campsAtuais;
    private List<Team> times;
    private List<Estatisticas> estatisticasTime;
    private List<Estatisticas> ests;
    private SportsIoMatches sportsIoMatches;
    private List<SportsIoMatches> matches;

    @PostConstruct
    public void init() {
        try {
            instanciar();
            
            this.matches = sportIoServico.pesquisar();
        } catch (Exception ex) {
            Logger.getLogger(ManagerIndex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void instanciar() {
        this.camp = new Campeonato();
        this.campsAtuais = new ArrayList<>();
        this.times = new ArrayList<>();
        this.estatisticasTime = new ArrayList<>();
        this.ests = new ArrayList<>();
        this.sportsIoMatches = new SportsIoMatches();
        this.matches = new ArrayList<>();
    }

    public void limpar() {
        instanciar();
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

    public List<Estatisticas> getEstatisticasTime() {
        return estatisticasTime;
    }

    public void setEstatisticasTime(List<Estatisticas> estatisticasTime) {
        this.estatisticasTime = estatisticasTime;
    }

    public List<Estatisticas> getEsts() {
        return ests;
    }

    public void setEsts(List<Estatisticas> ests) {
        this.ests = ests;
    }

    public SportsIoMatches getSportsIoMatches() {
        return sportsIoMatches;
    }

    public void setSportsIoMatches(SportsIoMatches sportsIoMatches) {
        this.sportsIoMatches = sportsIoMatches;
    }

    public List<SportsIoMatches> getMatches() {
        return matches;
    }

    public void setMatches(List<SportsIoMatches> matches) {
        this.matches = matches;
    }        

    public void mostrarCampeonato() {
        this.camp = campServico.buscaCamp(this.camp.getId());
        if (this.camp.getId() != null) {
            estatisticasCampIndex();
        }

    }

    public List<Estatisticas> estatisticasCampIndex() {
        if (this.camp != null && this.camp.getId() != null) {
            for (Team timeCamp : this.camp.getTeams()) {
                this.estatisticasTime = estatisticaServico.estatisticaPorTime(timeCamp.getId(), this.camp.getId());
                for (Estatisticas estats : this.estatisticasTime) {
                    //estats = estatisticaServico.pesquisar(estats.getId());
                    this.ests.add(estats);
                }
            }
        }
        return ests;
    }

    public void testeSports() {
        try {
            this.matches = sportIoServico.pesquisar();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
