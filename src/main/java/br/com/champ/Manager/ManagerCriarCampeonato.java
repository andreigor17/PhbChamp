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
            this.camp = this.campeonatoServico.find(Long.parseLong(visualizarCampId));
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

    public void salvarCampeonato() {
//        List<Player> jog = new ArrayList<>();
//        for (Team t : this.times) {
//            t = teamServico.find(t.getId());
//            for (Player p : t.getPlayers()) {
//                p = playerServico.buscaPlayer(p.getId());
//                jog.add(p);
//            }
//        }
//        this.camp.setPlayers(jog);
        this.camp.setTeams(this.times);
        this.camp.setStatus(StatusCamp.EM_ANDAMENTO);
        gerarPartidas();
        this.campeonatoServico.salvar(this.camp);

        for (Team timess : this.camp.getTeams()) {
            this.estatistica = new Estatisticas();
            this.estatistica.setTeam_id(timess.getId());
            this.estatistica.setCampeonato_id(this.camp.getId());
            this.estatisticaServico.salvar(estatistica);
            this.estatisticasTime.add(estatistica);
            timess.setEstatisticas(estatisticasTime);
            this.teamServico.update(timess);

        }
        Mensagem.successAndRedirect("Campeonato cadastrado com sucesso", "visualizarCampeonato.xhtml?id=" + this.camp.getId());
    }

    public List<Team> autoCompletarTime() {
        return teamServico.autoCompleteTime();
    }

    public void adicionarCamp() {
        this.times.add(this.time);
        this.time = new Team();

    }

    public void gerarPartidas() {
        List<Partida> matches = new ArrayList<>();
        Partida match;
        for (int i = 0; i < this.camp.getTeams().size() - 1; i++) {
            for (int j = i + 1; j < this.camp.getTeams().size(); j++) {
                match = new Partida(this.camp, this.camp.getTeams().get(i), this.camp.getTeams().get(j));
                matches.add(match);
            }
        }
        this.camp.setPartidas(matches);
    }

    public void limpar() {
        instanciar();
    }

    public void removeCamp() {
        this.campeonatoServico.delete(this.camp);
        Mensagem.successAndRedirect("pesquisarCampeonato.xhtml");
        init();
    }

    public void pesquisarCamp() {
        this.camps = campeonatoServico.pesquisar(this.camp);
    }

}
