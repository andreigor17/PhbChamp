package br.com.champ.Manager;

import br.com.champ.Modelo.Player;
import br.com.champ.Servico.TeamServico;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.util.ArrayList;
import javax.annotation.PostConstruct;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerTeam implements Serializable {
    
    @EJB
    TeamServico teamServico;
    @EJB
    PlayerServico playerServico;
    
    private Team team;
    private List<Team> times;
    private List<Player> membros;
    private Player membro;
    
    @PostConstruct
    public void init() {
        instanciar();
        
        String visualizarTeamId = FacesUtil
                .getRequestParameter("id");
        
        if (visualizarTeamId != null && !visualizarTeamId.isEmpty()) {
            this.team = this.teamServico.find(Long.parseLong(visualizarTeamId));
        }
    }
    
    public void instanciar() {
        this.team = new Team();
        this.times = new ArrayList<>();
        this.membros = new ArrayList<Player>();
        this.membro = new Player();
    }
    
    public Team getTeam() {
        return team;
    }
    
    public void setTeam(Team team) {
        this.team = team;
    }
    
    public List<Team> getTimes() {
        return times;
    }
    
    public void setTimes(List<Team> times) {
        this.times = times;
    }
    
    public void adicionarMembro() {
        System.out.println(membros);
        this.membros.add(this.membro);
        this.membro = new Player();
        
    }
    
    public void salvarTeam() {
        for (Player players : this.membros) {
            players.setPossuiTime(true);
            this.playerServico.update(players);
        }
        this.team.setPlayers(this.membros);
        this.teamServico.salvar(this.team);
        Mensagem.successAndRedirect("Time cadastrado com sucesso", "visualizarTime.xhtml?id=" + this.team.getId());
        
    }
    
    public void pesquisarTime() {
        this.times = teamServico.pesquisar(this.team);
    }
    
    public void limpar() {
        instanciar();
    }
    
    public void removeTime() {
        this.teamServico.delete(this.team);
        Mensagem.successAndRedirect("pesquisarTime.xhtml");
        init();
    }
    
    public List<Player> autoCompletarPlayer() {
        return playerServico.autoCompletePlayer();
    }
    
    public List<Player> getMembros() {
        return membros;
    }
    
    public void setMembros(List<Player> membros) {
        this.membros = membros;
    }
    
    public Player getMembro() {
        return membro;
    }
    
    public void setMembro(Player membro) {
        this.membro = membro;
    }
    
}
