package com.geekforless.accountservice.repository.entity;

import com.geekforless.accountservice.listener.RevisionAuditListener;
import lombok.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "revision_audit")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@RevisionEntity(RevisionAuditListener.class)
public class RevisionAudit implements Serializable {
    @Serial
    private static final long serialVersionUID = -687991492884005033L;

    @Id
    @SequenceGenerator(name = "idGeneratorCRE", sequenceName = "seq_pk_cre", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGeneratorCRE")
    @RevisionNumber
    @Column(name = "id")
    private Long id;

    @RevisionTimestamp
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "username")
    private String username;
}
