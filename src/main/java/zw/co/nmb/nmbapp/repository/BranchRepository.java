package zw.co.nmb.nmbapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.nmb.nmbapp.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
