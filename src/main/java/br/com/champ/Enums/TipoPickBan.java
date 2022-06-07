/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.champ.Enums;

/**
 *
 * @author andreigor
 */
public enum TipoPickBan {

    PICK(1, "PICK"),
    BAN(1, "BAN");

    private String nome;
    private Integer valor;

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    private TipoPickBan(Integer valor, String nome) {
        this.nome = nome;
        this.valor = valor;
    }

}
