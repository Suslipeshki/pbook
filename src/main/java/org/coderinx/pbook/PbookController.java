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

  @PutMapping("contacts")
  public void changeContact(@RequestBody Contact contact) {
    databaseService.change(contact);
  }

  @PostMapping("contacts")
  public Contact createContact (@RequestBody Contact contact) {
    return databaseService.create(contact);
  }

  @DeleteMapping("contacts/{id}")
  public void deleteContact(@PathVariable Integer id) {
    databaseService.delete(id);
  }
}