/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.ItemPartida;
import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.ItemPartidaServico;
import br.com.champ.Servico.PartidaServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Servico.TeamServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerPartida {

    @EJB
    private PartidaServico partidaServico;
    @EJB
    ItemPartidaServico itemPartidaServico;
    @EJB
    PlayerServico playerServico;
    @EJB
    TeamServico teamServico;
    private Partida partida;
    private Partida partidaPesquisar;
    private List<Player> playersTime1;
    private List<Player> playersTime2;
    private List<Partida> partidas;
    private List<ItemPartida> itensPartidas;
    private ItemPartida itemPartida;
    private int qtdItensPartidas;
    private String capitaoTime1;
    private String capitaoTime2;
    private String nomeTime1;
    private String nomeTime2;
    private List<ItemPartida> itemPartidas;
    private List<Player> allPlayers;
    private List<Player> selectedPlayers;
    private DualListModel<Player> playerGroupList;
    private Player player;
    private List<Player> players;

    @PostConstruct
    public void init() {

        String visualizarPartidaId = FacesUtil
                .getRequestParameter("id");

        if (visualizarPartidaId != null && !visualizarPartidaId.isEmpty()) {
            this.partida = this.partidaServico.pesquisar(Long.parseLong(visualizarPartidaId));

        } else {
            try {
                instanciar();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }

        if (this.partida.getId() != null) {
            this.itensPartidas = this.partida.getItemPartida();
            System.out.println("dados do item: " + this.itensPartidas.get(0).getTeam1().getNome());
        }

    }

    public void instanciar() throws Exception {
        this.partida = new Partida();
        this.partidas = new ArrayList<>();
        this.partidaPesquisar = new Partida();
        this.itemPartida = new ItemPartida();
        this.itensPartidas = new ArrayList<>();
        this.qtdItensPartidas = 1;
        this.selectedPlayers = new ArrayList<Player>();
        this.allPlayers = playerServico.pesquisar(this.player);
        this.playerGroupList = new DualListModel<>(this.allPlayers, this.selectedPlayers);
        this.itemPartidas = new ArrayList<>();
        this.itemPartida = new ItemPartida();

    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public List<Player> getPlayersTime1() {
        return playersTime1;
    }

    public void setPlayersTime1(List<Player> playersTime1) {
        this.playersTime1 = playersTime1;
    }

    public List<Player> getPlayersTime2() {
        return playersTime2;
    }

    public void setPlayersTime2(List<Player> playersTime2) {
        this.playersTime2 = playersTime2;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public Partida getPartidaPesquisar() {
        return partidaPesquisar;
    }

    public void setPartidaPesquisar(Partida partidaPesquisar) {
        this.partidaPesquisar = partidaPesquisar;
    }

    public void pesquisar() {
        this.partidas = partidaServico.pesquisarPartidas(this.partidaPesquisar);
    }

    public List<ItemPartida> getItensPartidas() {
        return itensPartidas;
    }

    public void setItensPartidas(List<ItemPartida> itensPartidas) {
        this.itensPartidas = itensPartidas;
    }

    public ItemPartida getItemPartida() {
        return itemPartida;
    }

    public void setItemPartida(ItemPartida itemPartida) {
        this.itemPartida = itemPartida;
    }

    public int getQtdItensPartidas() {
        return qtdItensPartidas;
    }

    public void setQtdItensPartidas(int qtdItensPartidas) {
        this.qtdItensPartidas = qtdItensPartidas;
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

    public String getNomeTime1() {
        return nomeTime1;
    }

    public void setNomeTime1(String nomeTime1) {
        this.nomeTime1 = nomeTime1;
    }

    public String getNomeTime2() {
        return nomeTime2;
    }

    public void setNomeTime2(String nomeTime2) {
        this.nomeTime2 = nomeTime2;
    }

    public List<ItemPartida> getItemPartidas() {
        return itemPartidas;
    }

    public void setItemPartidas(List<ItemPartida> itemPartidas) {
        this.itemPartidas = itemPartidas;
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

    public DualListModel<Player> getPlayerGroupList() {
        return playerGroupList;
    }

    public void setPlayerGroupList(DualListModel<Player> playerGroupList) {
        this.playerGroupList = playerGroupList;
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
    

    public void limpar() throws Exception {
        instanciar();
    }

    public void excluir() {

    }

    public List<ItemPartida> gerarPartidas(Partida p, Campeonato camp, Team time1, Team time2) {
   
        int i = 0;
        List<ItemPartida> partidasGeradas;
        try {
            for (i = 1; i < this.qtdItensPartidas; i++) {
                ItemPartida newItem = new ItemPartida();
                if (camp != null) {
                    newItem.setCamp(camp);
                }
                newItem.setTeam1(time1);
                newItem.setTeam2(time2);
                itemPartidaServico.salvar(newItem, null, Url.SALVAR_ITEM_PARTIDA.getNome());
                this.partida.getItemPartida().add(newItem);
                partidaServico.salvar(this.partida, this.partida.getId(), Url.ATUALIZAR_PARTIDA.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(ManagerPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void verificarPlayers() {
        this.selectedPlayers = this.playerGroupList.getTarget();
        if (this.selectedPlayers.size() % 2 == 0) {
            PrimeFaces.current().executeScript("PF('confirmarCriacaoX5Dialog').show();");
        } else {
            Mensagem.error("Para formar 2 times, adicione uma quantidade par de jogadores!");
            return;
        }
    }

    public void sorteioX5() {
        try { 
            List<Player> time1 = new ArrayList<>();
            List<Player> time2 = new ArrayList<>();
            Team t1 = new Team();
            Team t2 = new Team();
            Partida partidaX5 = new Partida();
            ItemPartida itemPartidaAtual = new ItemPartida();

            this.selectedPlayers = this.playerGroupList.getTarget();
            System.out.println("players selecionados: " + this.selectedPlayers.size());

            Collections.shuffle(this.selectedPlayers);
            for (int i = 0; i < this.selectedPlayers.size() / 2; i++) {
                this.selectedPlayers.get(i).setPossuiTime(true);
                System.out.println(this.nomeTime1 + " " + this.selectedPlayers.get(i).getNick());
                time1.add(this.selectedPlayers.get(i));
            }

            for (Player p : this.selectedPlayers) {
                if (!p.isPossuiTime()) {
                    System.out.println(this.nomeTime2 + " " + p.getNick());
                    time2.add(p);
                }

            }

            Team team1 = new Team();
            t1.setAtivo(true);
            t1.setNome(nomeTime1);
            t1.setPlayers(time1);
            team1 = teamServico.save(t1, null, Url.SALVAR_TIME.getNome());

            Team team2 = new Team();
            t2.setAtivo(true);
            t2.setNome(nomeTime2);
            t2.setPlayers(time2);
            team2 = teamServico.save(t2, null, Url.SALVAR_TIME.getNome());

            Partida partida = new Partida();
            itemPartida.setTeam1(team1);
            itemPartida.setTeam2(team2);
            itemPartida.setCamp(null);
//            itemPartidaAtual = itemPartidaServico.salvar(itemPartida, null, Url.SALVAR_ITEM_PARTIDA.getNome());

            this.itemPartidas.add(itemPartida);
            partidaX5.setItemPartida(this.itemPartidas);
            partida = partidaServico.salvar(partidaX5, null, Url.SALVAR_PARTIDA.getNome());

            Mensagem.successAndRedirect("Partida criada com sucesso", "visualizarPartida.xhtml?id=" + partida.getId());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
