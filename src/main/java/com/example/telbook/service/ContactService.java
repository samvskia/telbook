package com.example.telbook.service;

import com.example.telbook.dao.ContactDao;
import com.example.telbook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {
    private final ContactDao contactDao;

    @Autowired
    public ContactService(@Qualifier("H2DB") ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public int addContact (Contact contact) {
        return this.contactDao.insertContact(contact);
    }

    public List<Contact> getAllContacts() {
        return this.contactDao.selectAllContacts();
    }

    public Optional<Contact> getContactById(UUID id) {
        return this.contactDao.selectContactById(id);
    }

    public List<Contact> searchContacts(Contact contact) {
        return this.contactDao.selectAllMatchContacts(contact);
    }

    public int deleteContact(UUID id) {
        return this.contactDao.deleteContactById(id);
    }

    public int updateContact(UUID id, Contact newContact) {
        return this.contactDao.updateContactById(id, newContact);
    }
}
