/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.AnexoServico;
import br.com.champ.Servico.CampeonatoServico;
import br.com.champ.Servico.PartidaServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Servico.TeamServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerPlayer implements Serializable {

    @EJB
    PlayerServico playerServico;
    @EJB
    CampeonatoServico campServico;
    @EJB
    AnexoServico anexoServico;
    @EJB
    TeamServico teamServico;
    @EJB
    PartidaServico partidaServico;

    private Player player;
    private List<Player> players;
    private List<Campeonato> camps;
    private String fotoDoPlayer;
    private List<Player> allPlayers;
    private List<Player> selectedPlayers;
    private DualListModel<Player> playerGroupList;
    private String capitaoTime1;
    private String capitaoTime2;

    @PostConstruct
    public void init() {
        try {
            instanciar();

            String visualizarPlayerId = FacesUtil
                    .getRequestParameter("id");

            if (visualizarPlayerId != null && !visualizarPlayerId.isEmpty()) {
                this.player = this.playerServico.buscaPlayer(Long.parseLong(visualizarPlayerId));
            }

            this.camps = campServico.buscaCampPorPlayer(this.player);
        } catch (Exception ex) {
            Logger.getLogger(ManagerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void instanciar() throws Exception {
        this.player = new Player();
        this.players = new ArrayList<>();
        this.camps = new ArrayList<>();
        this.selectedPlayers = new ArrayList<Player>();
        this.allPlayers = playerServico.pesquisar(this.player);
        this.playerGroupList = new DualListModel<>(this.allPlayers, this.selectedPlayers);

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Campeonato> getCamps() {
        return camps;
    }

    public void setCamps(List<Campeonato> camps) {
        this.camps = camps;
    }

    public String getFotoDoPlayer() {
        return fotoDoPlayer;
    }

    public void setFotoDoPlayer(String fotoDoPlayer) {
        this.fotoDoPlayer = fotoDoPlayer;
    }

    public DualListModel<Player> getPlayerGroupList() {
        return playerGroupList;
    }

    public void setPlayerGroupList(DualListModel<Player> playerGroupList) {
        this.playerGroupList = playerGroupList;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public List<Player> getSelectedPlayers() {
        return selectedPlayers;
    }

    public void setSelectedPlayers(List<Player> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    public String getCapitaoTime1() {
        return capitaoTime1;
    }

    public void setCapitaoTime1(String capitaoTime1) {
        this.capitaoTime1 = capitaoTime1;
    }

    public String getCapitaoTime2() {
        return capitaoTime2;
    }

    public void setCapitaoTime2(String capitaoTime2) {
        this.capitaoTime2 = capitaoTime2;
    }

    public void salvarPlayer() {
        //this.playerServico.salvar(this.player);
        //Mensagem.successAndRedirect("Player salvo com sucesso", "visualizarPlayer.xhtml?id=" + this.player.getId());
    }

    public void pesquisarPlayer() throws Exception {
        this.players = playerServico.pesquisar(this.player);
    }

    public void limpar() throws Exception {
        instanciar();
    }

    public void removerPlayer() throws Exception {
        //this.playerServico.delete(this.player);
        Mensagem.successAndRedirect("pesquisarPlayer.xhtml");
        init();
    }

    public void sorteioX5() {
        try {
            List<Player> time1 = new ArrayList<>();
            List<Player> time2 = new ArrayList<>();
            Team t1 = new Team();
            Team t2 = new Team();
            Partida partidaX5 = new Partida();

            this.selectedPlayers = this.playerGroupList.getTarget();
            System.out.println("players selecionados: " + this.selectedPlayers.size());

            if (this.selectedPlayers.size() % 2 == 0) {
                System.out.println("ok");
            } else {
                Mensagem.error("Para formar 2 times, adicione uma quantidade par de jogadores!");
            }
            Collections.shuffle(this.selectedPlayers);
            for (int i = 0; i <= this.selectedPlayers.size()/2 ; i++) {
                this.selectedPlayers.get(i).setPossuiTime(true);
                System.out.println("Time 1: " + this.selectedPlayers.get(i));
                time1.add(this.selectedPlayers.get(i));
            }

            for (Player p : this.selectedPlayers) {
                if (!p.isPossuiTime()) {
                    System.out.println("Time 2: " + p);
                    time2.add(p);
                }

            }

            Team team1 = new Team();
            t1.setAtivo(true);
            t1.setNome("TimeTeste1 ");
            t1.setPlayers(time1);
            team1 = teamServico.save(t1, null, Url.SALVAR_TIME.getNome());

            Team team2 = new Team();
            t2.setAtivo(true);
            t2.setNome("TimeTeste2 ");
            t2.setPlayers(time2);
            team2 = teamServico.save(t2, null, Url.SALVAR_TIME.getNome());

            Partida partida = new Partida();
            partidaX5.setTeam1(team1);
            partidaX5.setTeam2(team2);
            partida = partidaServico.salvar(partidaX5, null, Url.SALVAR_PARTIDA.getNome());

            Mensagem.successAndRedirect("Partida criada com sucesso", "visualizarPartida.xhtml?id=" + partida.getId());
            
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
