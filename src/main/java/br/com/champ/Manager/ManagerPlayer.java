/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Modelo.Anexo;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Player;
import br.com.champ.Servico.AnexoServico;
import br.com.champ.Servico.CampeonatoServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

    private Player player;
    private List<Player> players;
    private List<Campeonato> camps;
    private String fotoDoPlayer;

    @PostConstruct
    public void init() {
        instanciar();

        String visualizarPlayerId = FacesUtil
                .getRequestParameter("id");

        if (visualizarPlayerId != null && !visualizarPlayerId.isEmpty()) {
            this.player = this.playerServico.buscaPlayer(Long.parseLong(visualizarPlayerId));
        }

        this.camps = campServico.buscaCampPorPlayer(this.player);
    }

    public void instanciar() {
        this.player = new Player();
        this.players = new ArrayList<>();
        this.camps = new ArrayList<>();
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

    public void salvarPlayer() {
        //this.playerServico.salvar(this.player);
        Mensagem.successAndRedirect("Player salvo com sucesso", "visualizarPlayer.xhtml?id=" + this.player.getId());
    }

    public void pesquisarPlayer() throws Exception {
        this.players = playerServico.pesquisar(this.player);
    }

    public void limpar() {
        instanciar();
    }

    public void removerPlayer() {
        //this.playerServico.delete(this.player);
        Mensagem.successAndRedirect("pesquisarPlayer.xhtml");
        init();
    }
    
}
