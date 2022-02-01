package zw.co.nmb.nmbapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nmb.nmbapp.model.Account;
import zw.co.nmb.nmbapp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {

        Account account = accountService.getById(id);

        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) throws Exception {
        Account dbAccount = accountService.create(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(dbAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
        Account dbAccount = accountService.update(id, account);

        return ResponseEntity.ok(dbAccount);
    }
}
