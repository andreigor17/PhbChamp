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
    private List<Player> playersTime1;
    private List<Player> playersTime2;

    @PostConstruct
    public void init() {

        instanciar();

        String visualizarPartidaId = FacesUtil
                .getRequestParameter("id");

        if (visualizarPartidaId != null && !visualizarPartidaId.isEmpty()) {
            this.partida = this.partidaServico.pesquisar(Long.parseLong(visualizarPartidaId));
        }
        
        if(this.partida != null){
            this.playersTime1 = this.partida.getTeam1().getPlayers();
            this.playersTime2 = this.partida.getTeam2().getPlayers();
        }

    }

    public void instanciar() {
        this.partida = new Partida();

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
    
    

    public void excluir() {

    }

}
