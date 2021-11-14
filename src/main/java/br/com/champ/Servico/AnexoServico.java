package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import javax.ejb.Stateless;
import br.com.champ.Modelo.Anexo;
import br.com.champ.Modelo.Player;
import br.com.champ.Utilitario.Mensagem;
import javax.persistence.Query;


/**
 *
 * @author andre
 */
@Stateless
public class AnexoServico extends ServicoGenerico<Anexo> {

    
   

    public AnexoServico() {
        super(Anexo.class);
    }
    
     public void salvar(Anexo anexo) {
        if (anexo.getId() == null) {
            save(anexo);
        } else {
            update(anexo);
        }
    }

    @Override
    public void delete(Anexo anexo) {
        anexo.setAtivo(false);
        super.remove(anexo);
    }
    
    

}
