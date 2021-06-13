package com.example.telbook.dao;

import com.example.telbook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("H2DB")
public class ContactDataAccessService implements ContactDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertContact(UUID id, Contact contact) {
        String sql = "INSERT INTO tbl_contacts (uuid, name, number) VALUES(?, ?, ?)";
        Object[] params = new Object[] { id, contact.getName(), contact.getNumber() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        this.jdbcTemplate.update(sql, params, types);
        return 0;
    }

    @Override
    public List<Contact> selectAllContacts() {
        String sql = "SELECT uuid, name, number FROM tbl_contacts";
        return this.jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("uuid"));
            String name = resultSet.getString("name");
            String number = resultSet.getString("number");
            return new Contact(uuid, name, number);
        });
    }

    @Override
    public Optional<Contact> selectContactById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Contact> selectAllMatchContacts(String text) {
        String sql = "SELECT uuid, name, number FROM tbl_contacts WHERE name LIKE '%" + text + "%'";
        return this.jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID uuid = UUID.fromString(resultSet.getString("uuid"));
            String name = resultSet.getString("name");
            String number = resultSet.getString("number");
            return new Contact(uuid, name, number);
        });
    }

    @Override
    public int deleteContactById(UUID id) {
        String sql = "DELETE FROM tbl_contacts WHERE uuid=?";
        Object[] params = new Object[] {id};
        int[] types = new int[] { Types.VARCHAR};
        this.jdbcTemplate.update(sql, params, types);
        return 0;
    }

    @Override
    public int updateContactById(UUID id, Contact contact) {
        String sql = "UPDATE tbl_contacts SET name=?, number=? WHERE uuid=?";
        Object[] params = new Object[] {contact.getName(), contact.getNumber(), id};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        this.jdbcTemplate.update(sql, params, types);
        return 0;
    }
}
