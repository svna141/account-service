package com.geekforless.accountservice.listener;

import com.geekforless.accountservice.repository.entity.RevisionAudit;
import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

@Service
public class RevisionAuditListener implements RevisionListener {

    public static final String SYSTEM_USER = "SYSTEM_USER";

    @Override
    public void newRevision(Object revisionEntity) {
        ((RevisionAudit) revisionEntity).setUsername(SYSTEM_USER);
    }
}
