package org.coderinx.pbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pbook")
public class PbookController {

  private final PbookDatabaseService databaseService;

  @Autowired
  public PbookController(PbookDatabaseService databaseService) {
    this.databaseService = databaseService;
  }

  @GetMapping("contacts")
  public List<Contact> getContacts() {
    return databaseService.findAll();
  }

  @GetMapping("contacts/{id}")
  public Contact getContact(@PathVariable Integer id) {
    return databaseService.find(id);
  }

  @PutMapping("contacts/{id}")
  public Contact changeContact(@PathVariable Integer id, @RequestBody Contact contact) {
    return databaseService.change(id, contact);
  }

  @PostMapping("contacts")
  public Contact createContact(@RequestBody Contact contact) {
    return databaseService.create(contact);
  }

  @DeleteMapping("contacts/{id}")
  public boolean deleteContact(@PathVariable Integer id) {
    return databaseService.delete(id);
  }
}
