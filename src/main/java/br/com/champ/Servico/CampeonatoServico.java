package br.com.champ.Servico;

import br.com.champ.Enums.StatusCamp;
import br.com.champ.Generico.ServicoGenerico;
import javax.ejb.Stateless;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Tabela;

/**
 *
 * @author andre
 */
import java.util.List;
import javax.persistence.Query;

@Stateless
public class CampeonatoServico extends ServicoGenerico<Campeonato> {

    public CampeonatoServico() {
        super(Campeonato.class);
    }

    public void salvar(Campeonato camp) {
        if (camp.getId() == null) {
            save(camp);
        } else {
            update(camp);
        }
    }

    @Override
    public void delete(Campeonato camp) {
        camp.setAtivo(false);
        super.remove(camp);
    }

    public List<Campeonato> pesquisar(Campeonato camp) {

        String sql = "select camp from Campeonato camp where ";

        sql += "camp.ativo = true";

        Query query = getEntityManager().createQuery(sql);

        return query.getResultList();

    }

    public List<Campeonato> buscaCampPorPlayer(Player player) {

        String sql = "select campeonato from Campeonato campeonato join campeonato.players player "
                + "where player = :player";

        Query query = getEntityManager().createQuery(sql);

        query.setParameter("player", player);

        return query.getResultList();

    }
    
    public List<Campeonato> autoCompleteCamps() {
        return pesquisarCampsAtuais();
    }

    
    public List<Campeonato> pesquisarCampsAtuais() {

        String sql = "select camp from Campeonato camp where ";

        sql += "camp.ativo = true";
        
        sql += " AND camp.status = :status";

        Query query = getEntityManager().createQuery(sql);
        
        query.setParameter("status", StatusCamp.EM_ANDAMENTO);

        return query.getResultList();

    }

}
