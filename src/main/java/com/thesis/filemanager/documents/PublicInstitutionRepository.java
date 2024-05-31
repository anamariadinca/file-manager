package com.thesis.filemanager.documents;

import com.thesis.filemanager.documents.models.PublicInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicInstitutionRepository extends JpaRepository<PublicInstitution, Long> {
    PublicInstitution findByUniqueIdentification(String uniqueIdentification);
}

