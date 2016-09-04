package com.codingmentorteam3.entities;

import com.codingmentorteam3.enums.NumItemsPerPageType;
import com.codingmentorteam3.enums.PageableTablesType;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@Table(name = "user_table")
public class User extends Person implements Serializable {

    @Column(name = "username", nullable = false, length = 30)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @MapKeyEnumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "num_item_per_page_table")
    @MapKeyColumn(name = "tables_enum")
    @Column(name = "num_enum")
    private Map<PageableTablesType, NumItemsPerPageType> numItemPerPage = new EnumMap(PageableTablesType.class);
    
    @OneToMany(mappedBy = "username", targetEntity = Role.class)
    private List<Role> roles;
    
    @OneToMany(mappedBy = "sender", targetEntity = Invitation.class)
    private List<Invitation> invitationsSent;
    
    @OneToMany(mappedBy = "user", targetEntity = Note.class)
    private List<Note> notes;
    
    @ManyToMany
    @JoinTable(name = "user_event_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;
    
    public User() {
        //Default constructor
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<PageableTablesType, NumItemsPerPageType> getNumItemPerPage() {
        return numItemPerPage;
    }

    public void setNumItemPerPage(Map<PageableTablesType, NumItemsPerPageType> numItemPerPage) {
        this.numItemPerPage = numItemPerPage;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Invitation> getInvitationsSent() {
        return invitationsSent;
    }

    public void setInvitationsSent(List<Invitation> invitationsSent) {
        this.invitationsSent = invitationsSent;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + Objects.hashCode(this.numItemPerPage);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.numItemPerPage != other.numItemPerPage) {
            return false;
        }
        return true;
    }

}
