/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Mapas;
import br.com.champ.Servico.MapaServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerMapa {

    @EJB
    MapaServico mapaServico;
    private Mapas mapa;
    private List<Mapas> mapas;
    ManagerAnexo arquivo = new ManagerAnexo();
    private UploadedFile file;
    private StreamedContent imagem;
    private String fotoMapa;

    @PostConstruct
    public void init() {

        String visualizarMapaId = FacesUtil
                .getRequestParameter("id");

        if (visualizarMapaId != null && !visualizarMapaId.isEmpty()) {
            this.mapa = this.mapaServico.pesquisarMapa(Long.parseLong(visualizarMapaId));

        } else {
            instanciar();
        }

    }

    public void instanciar() {
        this.mapa = new Mapas();
        this.mapas = new ArrayList<>();
    }

    public Mapas getMapa() {
        return mapa;
    }

    public void setMapa(Mapas mapa) {
        this.mapa = mapa;
    }

    public List<Mapas> getMapas() {
        return mapas;
    }

    public void setMapas(List<Mapas> mapas) {
        this.mapas = mapas;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }

    public String getFotoMapa() {
        return fotoMapa;
    }

    public void setFotoMapa(String fotoMapa) {
        this.fotoMapa = fotoMapa;
    }

    public void doUpload(FileUploadEvent event) {
        this.arquivo.fileUpload(event, ".png", "/image/"); //Tratamento de exceção.
        this.mapa.setAvatar(this.arquivo.getNome());
        imagem = new DefaultStreamedContent();
        this.setFile(event.getFile());

    }

    public void salvar() {
        Mapas m = new Mapas();
        try {
            m = mapaServico.save(this.mapa, null, Url.SALVAR_MAPA.getNome());
            this.arquivo.gravar();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        Mensagem.successAndRedirect("Mapa criado com sucesso", "visualizarMapas.xhtml?id=" + m.getId());

    }

    public void excluir() {

    }
}
