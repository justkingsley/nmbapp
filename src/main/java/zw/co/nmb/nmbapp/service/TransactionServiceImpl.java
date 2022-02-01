package zw.co.nmb.nmbapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.nmb.nmbapp.model.Transaction;
import zw.co.nmb.nmbapp.model.TransactionType;
import zw.co.nmb.nmbapp.repository.TransactionRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Transaction create(Transaction transaction) throws Exception {

        accountService.changeBalance(transaction);

        Transaction dbTransaction = transactionRepository.save(transaction);

        this.canAddCharge(dbTransaction);

        return this.getById(dbTransaction.getId());
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("transaction with " + id + " not found"));
    }

    @Override
    public Transaction reverse(Long id) {
        Transaction transaction = this.getById(id);

        Transaction updatedTransaction = transaction.toBuilder().reversed(true).build();

        return transactionRepository.save(updatedTransaction);
    }

    @Override
    public List<Transaction> getAllByAccountNumber(Long accountNumber) {
        return transactionRepository.findAllByAccountNumber(accountNumber);
    }

    private void canAddCharge(Transaction transaction) throws Exception {
        if(transaction.getTransactionType() == TransactionType.DR) {

            BigDecimal chargeAmount = transaction.getAmount().multiply(new BigDecimal("0.02"));

            Transaction transactionCharge = Transaction.builder()
                    .accountNumber(transaction.getAccountNumber())
                    .transactionType(TransactionType.DR)
                    .charge(true)
                    .reversed(false)
                    .amount(chargeAmount)
                    .build();

            transactionRepository.save(transactionCharge);

            accountService.changeBalance(transactionCharge);
        }
    }
}
