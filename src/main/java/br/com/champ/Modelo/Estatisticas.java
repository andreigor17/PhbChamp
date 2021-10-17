package br.com.champ.Modelo;

import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author andre
 */
@Entity
public class Estatisticas extends ModeloGenerico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer adr;
    private Integer mvp;
    private Integer hs;
    @ManyToOne
    private Player player;

}
