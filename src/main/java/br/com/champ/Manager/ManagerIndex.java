package br.com.champ.Manager;

import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Estatisticas;
import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.CampeonatoServico;
import br.com.champ.Servico.EstatisticaServico;
import br.com.champ.Servico.PartidaServico;
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
    PartidaServico partidaServico;
    @EJB
    CampeonatoServico campeonatoServico;

    private Campeonato camp;
    private List<Campeonato> campsAtuais;
    private List<Team> times;
    private List<Estatisticas> estatisticasTime;
    private List<Estatisticas> ests;
    private List<Partida> partidas;
    private List<Campeonato> camps;

    @PostConstruct
    public void init() {
        try {
            instanciar();
            this.partidas = partidaServico.pesquisarPartidasGeral();
            this.camps = campeonatoServico.pesquisar();
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
        this.partidas = new ArrayList<>();

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


    public List<Campeonato> getCamps() {
        return camps;
    }

    public void setCamps(List<Campeonato> camps) {
        this.camps = camps;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }


}
