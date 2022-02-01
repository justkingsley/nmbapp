package zw.co.nmb.nmbapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nmb.nmbapp.model.Transaction;
import zw.co.nmb.nmbapp.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll() {
        List<Transaction> transactions = transactionService.getAll();

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Long id) {
        Transaction transaction = transactionService.getById(id);

        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) throws Exception {
        Transaction dbTransaction = transactionService.create(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(dbTransaction);
    }

    @PutMapping("/reverse/{id}")
    public ResponseEntity<Transaction> reverse(@PathVariable Long id) {
        Transaction transaction = transactionService.reverse(id);

        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/accountNumber/{id}")
    public ResponseEntity<List<Transaction>> getAllByAccountNumber(@PathVariable Long id) {
        List<Transaction> transactions = transactionService.getAllByAccountNumber(id);

        return ResponseEntity.ok(transactions);
    }

}
