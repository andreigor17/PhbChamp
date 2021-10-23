package br.com.champ.Manager;

import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.CampeonatoServico;
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
public class ManagerCampeonato implements Serializable {

    @EJB
    TeamServico teamServico;
    @EJB
    PlayerServico playerServico;
    @EJB
    CampeonatoServico campeonatoServico;

    private Campeonato camp;
    private List<Campeonato> camps;
    private List<Team> times;
    private Team time;

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

    public void salvarCampeonato() {
        this.camp.setTeams(this.times);
        this.campeonatoServico.salvar(this.camp);
        Mensagem.successAndRedirect("Campeonato cadastrado com sucesso", "visualizarCampeonato.xhtml?id=" + this.camp.getId());
    }

    public List<Team> autoCompletarTime() {
        return teamServico.autoCompleteTime();
    }

    public void adicionarCamp() {
        this.times.add(this.time);
        this.time = new Team();

    }
    
    public void gerarTabela(){
            
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
