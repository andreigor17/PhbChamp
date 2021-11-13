package br.com.champ.Manager;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerAnexo implements Serializable{
    
    public void doUpload(FileUploadEvent fileUploadEvent) {
             UploadedFile uploadedFile = fileUploadEvent.getFile();
             String fileNameUploaded = uploadedFile.getFileName();
             long fileSizeUploaded = uploadedFile.getSize(); 
             String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded              		+"</b><br/>"+
                 "Tamanho do Arquivo: <b>"+fileSizeUploaded+"</b>";
             System.out.println(infoAboutFile);
             FacesContext facesContext = FacesContext.getCurrentInstance();
             facesContext.addMessage(null, new FacesMessage("Sucesso", 			                                                                       infoAboutFile));
   }
    
}
