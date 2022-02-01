package zw.co.nmb.nmbapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountNumber;
    @CreatedDate
    @Column(updatable = false)
    private Date dateOfCreation;
    private String currency;
    private String mobileNumber;
    @ManyToOne
    private Branch branch;
    @Builder.Default
    private BigDecimal initialBalance = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal activeBalance = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal debitAmount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal creditAmount = BigDecimal.ZERO;
    @Builder.Default
    private String debitNarration = "";
    @Builder.Default
    private String creditNarration = "";
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    private List<Transaction> transactions = Collections.emptyList();
}
