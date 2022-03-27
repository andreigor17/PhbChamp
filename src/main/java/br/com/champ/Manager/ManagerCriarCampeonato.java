package br.com.champ.Manager;

import br.com.champ.Enums.StatusCamp;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Estatisticas;
import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.CampeonatoServico;
import br.com.champ.Servico.EstatisticaServico;
import br.com.champ.Servico.PartidaServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Servico.TeamServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerCriarCampeonato implements Serializable {

    @EJB
    TeamServico teamServico;
    @EJB
    PlayerServico playerServico;
    @EJB
    CampeonatoServico campeonatoServico;
    @EJB
    EstatisticaServico estatisticaServico;
    @EJB
    PartidaServico partidaServico;

    private Campeonato camp;
    private List<Campeonato> camps;
    private List<Team> times;
    private Team time;
    private List<Estatisticas> estatisticasTime;
    private Estatisticas estatistica;

    @PostConstruct
    public void init() {
        instanciar();

        String visualizarCampId = FacesUtil
                .getRequestParameter("id");

        if (visualizarCampId != null && !visualizarCampId.isEmpty()) {
            this.camp = this.campeonatoServico.buscaCamp(Long.parseLong(visualizarCampId));
        }

    }

    public void instanciar() {
        this.camp = new Campeonato();
        this.camps = new ArrayList<>();
        this.time = new Team();
        this.times = new ArrayList<>();
        this.estatisticasTime = new ArrayList<>();

    }

    public Campeonato getCamp() {
        return camp;
    }

    public void setCamp(Campeonato camp) {
        this.camp = camp;
    }

    public List<Campeonato> getCamps() {
        return camps;
    }

    public void setCamps(List<Campeonato> camps) {
        this.camps = camps;
    }

    public List<Team> getTimes() {
        return times;
    }

    public void setTimes(List<Team> times) {
        this.times = times;
    }

    public Team getTime() {
        return time;
    }

    public void setTime(Team time) {
        this.time = time;
    }

    public List<Estatisticas> getEstatisticasTime() {
        return estatisticasTime;
    }

    public void setEstatisticasTime(List<Estatisticas> estatisticasTime) {
        this.estatisticasTime = estatisticasTime;
    }

    public Estatisticas getEstatistica() {
        return estatistica;
    }

    public void setEstatistica(Estatisticas estatistica) {
        this.estatistica = estatistica;
    }

    public void salvarCampeonato() throws Exception {

        this.camp.setTeams(this.times);

        this.camp.setStatus(StatusCamp.EM_ANDAMENTO);        
        this.camp.setPartidas(gerarPartidas());

        Campeonato c =  new Campeonato();
        c = campeonatoServico.save(this.camp);
        
         for (Team timess : this.camp.getTeams()) {
            this.estatistica = new Estatisticas();
            this.estatistica.setTeam_id(timess.getId());
            this.estatistica.setCampeonato_id(c.getId());
            this.estatisticaServico.salvar(estatistica, null);
            this.estatisticasTime.add(estatistica);
            timess.setEstatisticas(estatisticasTime);
            //this.teamServico.update(timess);

        }

        Mensagem.successAndRedirect("Camp salvo", "visualizarCampeonato.xhtml?id=" + c.getId());
    }

    public List<Team> autoCompletarTime() throws Exception {
        return teamServico.autoCompleteTime();
    }

    public void adicionarCamp() {
        this.times.add(this.time);
        this.time = new Team();

    }

    public List<Partida> gerarPartidas() throws Exception {
        List<Partida> matches = new ArrayList<>();
        Partida match = new Partida();
        for (int i = 0; i < this.times.size() - 1; i++) {
            for (int j = i + 1; j < this.times.size(); j++) {
                match = new Partida(this.times.get(i), this.times.get(j));
                //this.partidaServico.salvar(match);
                matches.add(match);
            }
        }
        System.out.println("Partidas " + matches);
        return matches;
    }

    public void limpar() {
        instanciar();
    }

//    public void removeCamp() {
//        this.campeonatoServico.delete(this.camp);
//        Mensagem.successAndRedirect("pesquisarCampeonato.xhtml");
//        init();
//    }
    public void pesquisarCamp() throws Exception {
        this.camps = campeonatoServico.pesquisar(this.camp);
    }

}
