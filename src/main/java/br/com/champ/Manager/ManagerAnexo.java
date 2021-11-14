package br.com.champ.Manager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andre
 */
@ManagedBean
@SessionScoped
public class ManagerAnexo implements Serializable {

    private String diretorio;
    private String caminho;
    private byte[] arquivo;
    private String nome;
    public String REAL_PATH_OPT = "/opt/uploads";

    public ManagerAnexo() {
    }

    public String getNome() {
        return nome;
    }

    public void fileUpload(FileUploadEvent event, String type, String diretorio) {
        try {
            Timestamp playerAvatar = new Timestamp(System.currentTimeMillis());
            this.nome = playerAvatar + type;
            this.caminho = REAL_PATH_OPT + diretorio + getNome();
            this.arquivo = event.getFile().getContents();

            File file = new File(REAL_PATH_OPT + diretorio);
            file.mkdirs();

        } catch (Exception ex) {
            System.out.println("Erro no upload do arquivo" + ex);
        }
    }

    public void gravar() {

        try {

            FileOutputStream fos;
            fos = new FileOutputStream(this.caminho);
            fos.write(this.arquivo);
            System.out.println("caminho do arquivo " + this.caminho);
            fos.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

     }
