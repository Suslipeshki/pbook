package org.coderinx.pbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PbookDatabaseService {

    private Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/phonebook",
                    "postgres", "gjKjYtp96");
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contact> findAll() {
        try (Connection connection = createConnection()) {
            List<Contact> contacts = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
            while (resultSet.next()) {
                Contact contact;
                contact = new Contact(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDate("bday"), resultSet.getLong("pnum"));
                contacts.add(contact);
            }
            return contacts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Contact find(Integer id) {
        try (Connection connection = createConnection()) {
            List<Contact> contacts = new ArrayList<>();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("SELECT * FROM contacts WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Contact contact;
                contact = new Contact(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDate("bday"), resultSet.getLong("pnum"));
                contacts.add(contact);
            }
            if (contacts.size() == 1) {
                return contacts.get(0);
            }
            if (contacts.size() == 0) {
                return null;
            } else {
                throw new RuntimeException("returned more than one result");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void change(Contact contact) {
        try (Connection connection = createConnection()) {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "UPDATE contacts SET name = ?, bday = ?, pnum = ? WHERE id = ?"
            );
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setDate(2, contact.getBday());
            preparedStatement.setLong(3, contact.getPnum());
            preparedStatement.setInt(4, contact.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Contact create(Contact contact) {
        try (Connection connection = createConnection()) {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO contacts (name, bday, pnum)" +
                    "VALUES (?, ?, ?)");
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setDate(2, contact.getBday());
            preparedStatement.setLong(3, contact.getPnum());
            preparedStatement.executeUpdate();
            return contact;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        try (Connection connection = createConnection()) {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE FROM contacts WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
