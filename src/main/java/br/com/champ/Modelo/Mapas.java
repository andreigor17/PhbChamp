/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Modelo;

import br.com.champ.Generico.ModeloGenerico;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author andre
 */
public class Mapas extends ModeloGenerico implements Serializable {

    private String nome;
    private List<ItemPartida> itemPartida;
    private String avatar;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemPartida> getItemPartida() {
        return itemPartida;
    }

    public void setItemPartida(List<ItemPartida> itemPartida) {
        this.itemPartida = itemPartida;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
