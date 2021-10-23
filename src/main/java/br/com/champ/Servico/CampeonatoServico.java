package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import javax.ejb.Stateless;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Tabela;

/**
 *
 * @author andre
 */
import java.util.List;
import javax.persistence.Query;
@Stateless
public class CampeonatoServico extends ServicoGenerico<Campeonato> {
    
    
    
    public CampeonatoServico(){
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

        sql += "camp.ativo = true and t.nome like '" + camp.getNome() + "'";

        Query query = getEntityManager().createQuery(sql);

        return query.getResultList();

    }
    
    public List<Object> gerarTabela(Long id) {
        System.out.println("----------------------------------------------------------1");
        String sql = "select t.nome, stats.roundsganhos, stats.roundsperdidos, stats.partidas, stats.pontos, stats.kill, stats.death, stats.assists from team t inner join estatisticas as stats on t.id = stats.team_id where stats.campeonato_id = "+id;
        Query query = getEntityManager().createQuery(sql);
        System.out.println("----------------------------------------------------------2");
        List<Object> tabela = query.getResultList();
        System.out.println("----------------------------------------------------------");
        System.out.println(tabela.get(0).toString());
        return tabela;
    }
   
    
}
