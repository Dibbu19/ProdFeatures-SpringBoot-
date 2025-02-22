package com.prod.features.controllers;

import com.prod.features.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/post/{postId}")
    private List<PostEntity> getPostRevisions(@PathVariable Long postId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<Number> revisions = auditReader.getRevisions(PostEntity.class, postId);
        return revisions.stream()
                .map(revisionNumber -> auditReader.find(PostEntity.class,postId,revisionNumber))
                .collect(Collectors.toList());
    }
}
