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
import com.sun.javafx.scene.control.skin.VirtualFlow;
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
    private Player playerOne;
    private Player playerTwo;

    private Team team;
    private List<Team> times;

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

    public void salvarTeam() {

        if (playerOne != null && playerTwo != null) {
            List<Player> jogadores = new ArrayList<>();
            jogadores.add(playerOne);
            jogadores.add(playerTwo);
            this.team.setPlayers(jogadores);
        }
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

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

}
