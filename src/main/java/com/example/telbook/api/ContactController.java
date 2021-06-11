package com.example.telbook.api;

import com.example.telbook.model.Contact;
import com.example.telbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/contact")
@RestController
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public void addContact(@Valid @NotNull @RequestBody Contact contact) {
        this.contactService.addContact(contact);
    }

    @GetMapping
    public List<Contact> getAllPeople() {
        return this.contactService.getAllContacts();
    }

    @GetMapping(path = "{id}")
    public Contact getContactById(@PathVariable("id") UUID id) {
        return this.contactService.getContactById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteContactById(@PathVariable("id") UUID id) {
        this.contactService.deleteContact(id);
    }

    @PutMapping(path = "{id}")
    public void updateContact(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Contact newContact) {
        this.contactService.updateContact(id, newContact);
    }
}
