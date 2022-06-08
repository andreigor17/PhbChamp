package br.com.champ.Manager;

import br.com.champ.Utilitario.Mensagem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

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
    public String pathWindows = "D:\\uploads";

    public ManagerAnexo() {
    }

    public String getNome() {
        return nome;
    }

    public void fileUpload(FileUploadEvent event, String type, String diretorio) {
        try {
            String path = null;
            Timestamp playerAvatar = new Timestamp(System.currentTimeMillis());
            this.nome = playerAvatar + type;
            File ubuntuFile = new File(REAL_PATH_OPT);
            File windowsFile = new File(pathWindows);

            if (ubuntuFile.exists()) {
                path = REAL_PATH_OPT;
            } else {
                path = pathWindows;
            }
            if (!ubuntuFile.exists() && !windowsFile.exists()) {
                Mensagem.fatal("Nenhum arquivo de configuração encontrado!");
                return;
            }
            this.caminho = path + diretorio + getNome();
            this.arquivo = event.getFile().getContent();

            File file = new File(path + diretorio);
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
