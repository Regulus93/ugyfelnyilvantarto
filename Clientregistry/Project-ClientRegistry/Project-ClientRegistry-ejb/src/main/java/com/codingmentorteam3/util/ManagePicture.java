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

/**
 *
 * @author Bicsak Daniel
 */
@ManagedBean
@ViewScoped
public class ManagePicture {

    private Part uploadedFile;

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part) value;
        if (file.getSize() > 1048576) {
            msgs.add(new FacesMessage("File bigger than a limit (1 MB)."));
        }
        if (!"image/jpeg".equals(file.getContentType())) {
            msgs.add(new FacesMessage("Please select a file with type: .jpeg or .jpg."));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public void uploadFile() throws IOException {
        try {
          File uploads = new File(System.getProperty("jboss.home.dir"), "\\standalone\\images");
            System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
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
