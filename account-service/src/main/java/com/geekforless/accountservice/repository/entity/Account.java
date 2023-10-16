package com.geekforless.accountservice.repository.entity;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table(name="accounts")
@EqualsAndHashCode(of = "id")
public class Account {
    @Id
    private Long id;

    private BigDecimal balance;

}
