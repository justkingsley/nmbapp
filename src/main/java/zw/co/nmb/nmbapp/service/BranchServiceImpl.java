package zw.co.nmb.nmbapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import zw.co.nmb.nmbapp.model.Branch;
import zw.co.nmb.nmbapp.repository.BranchRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch getById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("branch with id " + id + " not found"));
    }

    @Override
    public Branch create(Branch branch) throws Exception {

        try {
            return branchRepository.save(branch);
        } catch(DataIntegrityViolationException e) {
            throw new Exception(branch.getName() + " branch name already exists");
        } catch (Exception e) {
            throw new Exception("error saving branch");
        }
    }

    @Override
    public Branch update(Long id, Branch branch) {
        Branch dbBranch = this.getById(id);

        Branch updatedBranch = dbBranch.toBuilder()
                .name(branch.getName())
                .build();

        return branchRepository.save(updatedBranch);
    }

    @Override
    public Branch delete(Long id) {
        Branch dbBranch = getById(id);

        branchRepository.delete(dbBranch);

        return dbBranch;
    }
}
