/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Player;
import br.com.champ.Servico.PartidaServico;
import br.com.champ.Utilitario.FacesUtil;
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
public class ManagerPartida {

    @EJB
    private PartidaServico partidaServico;
    private Partida partida;
    private Partida partidaPesquisar;
    private List<Player> playersTime1;
    private List<Player> playersTime2;
    private List<Partida> partidas;

    @PostConstruct
    public void init() {

        instanciar();

        String visualizarPartidaId = FacesUtil
                .getRequestParameter("id");

        if (visualizarPartidaId != null && !visualizarPartidaId.isEmpty()) {
            this.partida = this.partidaServico.pesquisar(Long.parseLong(visualizarPartidaId));
        }

        if (this.partida.getId() != null) {
            this.playersTime1 = this.partida.getTeam1().getPlayers();
            this.playersTime2 = this.partida.getTeam2().getPlayers();
        }

    }

    public void instanciar() {
        this.partida = new Partida();
        this.partidas = new ArrayList<>();
        this.partidaPesquisar = new Partida();

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

    public void limpar() {
        instanciar();
    }

    public void excluir() {

    }

}