package com.example.telbook.dao;

import com.example.telbook.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("H2DB")
public class ContactDataAccessService implements ContactDao {

    @Override
    public int insertContact(UUID id, Contact contact) {
        return 0;
    }

    @Override
    public List<Contact> selectAllContacts() {
        return List.of(new Contact(UUID.randomUUID(), "FROM H2DB", "123456"));
    }

    @Override
    public Optional<Contact> selectContactById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteContactById(UUID id) {
        return 0;
    }

    @Override
    public int updateContactById(UUID id, Contact contact) {
        return 0;
    }
}
