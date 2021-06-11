package com.example.telbook.dao;

import com.example.telbook.model.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactDao {

    int insertContact(UUID id, Contact contact);

    default int insertContact(Contact contact) {
        UUID id = UUID.randomUUID();
        return insertContact(id, contact);
    }

    List<Contact> selectAllContacts();

    Optional<Contact> selectContactById(UUID id);

    int deleteContactById(UUID id);

    int updateContactById(UUID id, Contact contact);
}
