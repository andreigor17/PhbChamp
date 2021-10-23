/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import br.com.champ.Modelo.Estatisticas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author andre
 */
@Stateless
public class EstatisticaServico extends ServicoGenerico<Estatisticas> {

    private Estatisticas estatisticas;

    public void instanciar() {
        this.estatisticas = new Estatisticas();

    }

    public EstatisticaServico() {
        super(Estatisticas.class);
    }

    public void salvar(Estatisticas estatistica) {
        if (estatistica.getId() == null) {
            save(estatistica);
        } else {
            update(estatistica);
        }
    }

    @Override
    public void delete(Estatisticas estatistica) {
        estatistica.setAtivo(false);
        super.remove(estatistica);
    }

    public List<Estatisticas> estatisticaPorTime(Long id, Long id2) {

        String sql = "select est from Estatisticas est where ";

        sql += "est.ativo = true";

        if (id != null) {
            sql += " and team_id = " + id;
        }

        if (id2 != null) {
            sql += " and est.campeonato_id =" + id2;
        }

        Query query = getEntityManager().createQuery(sql);

        return query.getResultList();

    }

}
