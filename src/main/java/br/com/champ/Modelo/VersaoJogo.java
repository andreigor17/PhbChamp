/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Modelo;

import br.com.champ.Generico.ModeloGenerico;

/**
 *
 * @author andre
 */
public class VersaoJogo extends ModeloGenerico {

    private String nome;
    private String anexo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

}
