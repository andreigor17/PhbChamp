package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import javax.ejb.Stateless;
import br.com.champ.Modelo.Campeonato;

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
   
    
}
