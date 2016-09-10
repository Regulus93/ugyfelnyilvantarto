package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.User;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class NoteDTO {

    private String label;

    private String content;

    private User user;

    private Event event;

    public NoteDTO() {
        // Default constructor
    }

    public NoteDTO(Note note) {
        this.label = note.getLabel();
        this.content = note.getContent();
        this.user = note.getUser();
        this.event = note.getEvent();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.label);
        hash = 97 * hash + Objects.hashCode(this.content);
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.event);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NoteDTO other = (NoteDTO) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.event, other.event)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NoteDTO{" + "label=" + label + ", content=" + content + ", user=" + user + ", event=" + event + '}';
    }
    
}
