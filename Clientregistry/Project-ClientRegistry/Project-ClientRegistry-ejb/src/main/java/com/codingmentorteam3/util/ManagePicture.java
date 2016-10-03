package com.codingmentorteam3.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import org.jboss.logging.Logger;

/**
 *
 * @author Bicsak Daniel
 */
@ManagedBean
@ViewScoped
public class ManagePicture {

        private Part uploadedFile;
    private String fileContent;

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part) value;
//        if (file.getSize() > 1024) {
//            msgs.add(new FacesMessage("file too big"));
//        }
//        if (!"text/plain".equals(file.getContentType())) {
//            msgs.add(new FacesMessage("not a text file"));
//        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public void uploadFile() throws IOException {
        try {
          File uploads = new File(System.getProperty("jboss.home.dir"), "\\standalone\\images");
            System.out.println(uploads.toString());
            File file = new File(uploads, "dummy.jpg");
            InputStream input = uploadedFile.getInputStream();
            Files.copy(input, file.toPath());
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "error uploading file",
                    null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    
}
