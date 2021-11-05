/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Partida;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author andre
 */
@Stateless
public class PartidaServico extends ServicoGenerico<Partida>{
    
    private Partida partida;

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    
    
    public PartidaServico() {
        super(Partida.class);
    }

    public void salvar(Partida partida) {
        if (partida.getId() == null) {
            save(partida);
        } else {
            update(partida);
        }
    }

    @Override
    public void delete(Partida partida) {
        partida.setAtivo(false);
        super.remove(partida);
    }
    
    public List<Partida> partidaPorCamp(Long id) {
        String sql = "select p from Partida p where ";

        sql += "p.ativo = true";
        
        sql += " AND p.campeonato_id = " + id;

        Query query = getEntityManager().createQuery(sql);
        
        return query.getResultList();
    }
    
}
