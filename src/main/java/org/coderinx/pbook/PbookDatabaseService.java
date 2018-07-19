package org.coderinx.pbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PbookDatabaseService {


  public List<Contact> findAll() {
    try {
      List<Contact> contacts = new ArrayList<>();
      Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pbook", "pbook", "pbook");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from contacts");
      while (resultSet.next()) {
        Contact contact = new Contact(resultSet.getInt("id"), resultSet.getString("name"));
        contacts.add(contact);
      }
      return contacts;
    }
    catch (SQLException e) {
      throw new RuntimeException("Что-то пошло не так", e);
    }
  }

  public Contact find(Integer id) {
    return null;
  }

  public Contact change(Integer id, Contact contact) {
    return null;
  }

  public Contact create(Contact contact) {
    return null;
  }

  public boolean delete(Integer id) {
    return false;
  }
}
