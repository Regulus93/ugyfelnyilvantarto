package com.codingmentorteam3.controllers;

import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.dtos.PersonDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Person;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.PersonService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "personController")
public class PersonController {

    @Inject
    private PersonService personService;

    protected Person loadEntity(Long entityId) {
        if (entityId != null) {
            return personService.getPerson(entityId);
        }
        return null;
    }

    public List<ConnectionChannelDTO> getConnectionChannels(Long personId) {
        Person currentPerson = personService.getPerson(personId);
        if (null != currentPerson) {
            List<ConnectionChannelDTO> connectionChannelDTOs = new ArrayList<>();
            for (ConnectionChannel ch : personService.getConnectionChannelsListByPersonId(personId)) {
                ConnectionChannelDTO connectionChannelDTO = new ConnectionChannelDTO(ch);
                connectionChannelDTOs.add(connectionChannelDTO);
            }
            return connectionChannelDTOs;
        }
        throw new BadRequestException("No person found in database!");
    }

    public PersonDTO changeAvatar(String avatarPath, Long personId) {
        Person currentPerson = loadEntity(personId);
        if (null != currentPerson) {
            currentPerson.setAvatar(avatarPath);
            personService.editPerson(currentPerson);
            return new PersonDTO(currentPerson);
        }
        throw new BadRequestException("No person found in database!");
    }

    public String getAvatar(Long personId) {
        return personService.getPerson(personId).getAvatar();
    }

}
