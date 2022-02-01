package zw.co.nmb.nmbapp.service;

import zw.co.nmb.nmbapp.model.Branch;

import java.util.List;

public interface BranchService {
    List<Branch> getAll();
    Branch getById(Long id);
    Branch create(Branch branch) throws Exception;
    Branch update(Long id, Branch branch);
    Branch delete(Long id);
}
