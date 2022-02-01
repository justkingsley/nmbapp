package zw.co.nmb.nmbapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountNumber;
    @Enumerated
    private TransactionType transactionType;
    @Builder.Default
    private boolean charge = false;
    @Builder.Default
    private boolean reversed = false;
    @Builder.Default
    private boolean interest = false;
    private BigDecimal amount;
    @CreatedDate
    @Column(updatable = false)
    private Date date;
}
