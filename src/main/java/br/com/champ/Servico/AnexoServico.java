package br.com.champ.Servico;

import javax.ejb.Stateless;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import org.apache.commons.lang.SystemUtils;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author andre
 */
@Stateless
public class AnexoServico {

    private String diretorio;
    private String caminho;
    private byte[] arquivo;
    private String nome;
    public static final String REAL_PATH_OPT = "/opt/uploads/images/";
    public static final String REAL_PATH_OPT_WINDOWS = "D:\\uploads\\images\\";

    public String getNome() {
        return nome;
    }

    public static String getRealPath() {
        if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
            System.out.println("1");
            return REAL_PATH_OPT;
        } else {
            System.out.println("2");
            return REAL_PATH_OPT_WINDOWS;
        }
    }

    public void fileUpload(FileUploadEvent event, String type) {
        try {
            String osPath = null;            
            this.nome = event.getFile().getFileName() + type;

            osPath = getRealPath();

            File path = new File(osPath);
            if (!path.exists()) {
                path.mkdir();
            }

            this.caminho = path.getAbsolutePath() + "/";
            this.arquivo = event.getFile().getContent();
            this.nome = event.getFile().getFileName();
            gravar();

        } catch (Exception ex) {
            System.out.println("Erro no upload do arquivo" + ex);
        }
    }

    public void gravar() {

        try {

            FileOutputStream fos;
            fos = new FileOutputStream(this.caminho + this.nome);
            fos.write(this.arquivo);
            System.out.println("caminho do arquivo " + this.caminho);
            fos.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
