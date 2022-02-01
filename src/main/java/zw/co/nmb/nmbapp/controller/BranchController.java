package zw.co.nmb.nmbapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nmb.nmbapp.model.Branch;
import zw.co.nmb.nmbapp.service.BranchService;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<Branch>> getAll() {
        List<Branch> branches = branchService.getAll();

        return ResponseEntity.ok(branches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getById(@PathVariable Long id) {
        Branch branch = branchService.getById(id);

        return ResponseEntity.ok(branch);
    }

    @PostMapping
    public ResponseEntity<Branch> create(@RequestBody Branch branch) throws Exception {
        Branch dbBranch = branchService.create(branch);

        return ResponseEntity.status(HttpStatus.CREATED).body(dbBranch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> update(@PathVariable Long id, @RequestBody Branch branch) {
        Branch dbBranch = branchService.update(id, branch);

        return ResponseEntity.ok(dbBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Branch> delete(@PathVariable Long id) {
        Branch branch = branchService.delete(id);

        return ResponseEntity.ok(branch);
    }
}
