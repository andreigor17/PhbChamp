/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author andre
 */
@Stateless
public class PlayerServico extends ServicoGenerico<Player> {

    private Player player;

    public void instanciar() {
        this.player = new Player();

    }

    public PlayerServico() {
        super(Player.class);
    }

    public void salvar(Player player) {
        if (player.getId() == null) {
            save(player);
        } else {
            update(player);
        }
    }

    @Override
    public void delete(Player player) {
        player.setAtivo(false);
        super.remove(player);
    }

    public List<Player> pesquisar(Player player) {

        String sql = "select p from Player p where ";

        sql += "p.ativo = true";

        Query query = getEntityManager().createQuery(sql);

        return query.getResultList();

    }

    public List<Player> autoCompletePessoa() {
        return buscaPlayers();
    }

    private List<Player> buscaPlayers() {
        String sql = "select player from Player player where player.ativo = true";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public List<Player> autoCompletePlayer() {
        return buscarPorPlayer();
    }

    public List<Player> buscarPorPlayer() {
        String sql = "select p from Player p where p.ativo = true and p.team is null ";

        Query query = getEntityManager().createQuery(sql);

        return query.getResultList();

    }

}
