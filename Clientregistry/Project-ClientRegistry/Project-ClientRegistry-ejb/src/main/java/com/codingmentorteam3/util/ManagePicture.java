package com.codingmentorteam3.util;

import com.codingmentorteam3.controllers.PersonController;
import com.codingmentorteam3.controllers.UserController;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Bicsak Daniel
 */
@ManagedBean
@ViewScoped
public class ManagePicture {

    private Part uploadedFile;

    private static final Logger LOG = Logger.getLogger(ManagePicture.class.getName());

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part) value;
        if (file.getSize() > 1048576) {
            msgs.add(new FacesMessage("File bigger than a limit (1 MB)."));
        }
        if (!"image/jpeg".equals(file.getContentType()) || !msgs.isEmpty()) {
            msgs.add(new FacesMessage("Please select a file with type: .jpeg or .jpg."));
        }
    }

    public void uploadFile() throws IOException {
        try {
            String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
            File uploads = new File(System.getProperty("jboss.home.dir"), "\\standalone\\images");
            File f = new File(System.getProperty("jboss.home.dir"), "\\standalone\\images\\" + username + ".jpg");
            if (f.exists()) {
                f.delete();
            }
            File file = new File(uploads, username + ".jpg");
            InputStream input = uploadedFile.getInputStream();
            Files.copy(input, file.toPath());
            setUserPicturePathInEntity(file.toPath().toString(), username);
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "error uploading file",
                    null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void setUserPicturePathInEntity(String path, String username) {
        UserController uc = new UserController();

        long personId = uc.getUserIdByUsername(username);

        PersonController pc = new PersonController();
        pc.changeAvatar(path, personId);
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public String getUserPicturePath() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        File f = new File(System.getProperty("jboss.home.dir"), "\\standalone\\images\\" + username + ".jpg");
        return f.toPath().toString();
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
