/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.champ.Enums;

import java.util.List;

/**
 *
 * @author andreigor
 */
public enum TipoPickBan {

    MD1("PICK", "PICK", "BAN", "BAN", "BAN", "BAN", "BAN"),
    MD2("PICK", "BAN", "BAN", "BAN", "BAN", "BAN", "BAN"),
    MD3("PICK", "BAN", "BAN", "BAN", "BAN", "BAN", "BAN"),
    MD5("PICK", "BAN", "BAN", "BAN", "BAN", "BAN", "BAN");

    private String nome;
    private List<String> nomes;

    public String getNome() {
        return nome;
    }
        
    private TipoPickBan(String nome1, String nome2, String nome3, String nome4, String nome5, String nome6, String nome7) {
        this.nome = nome;

    }

}
