package com.thesis.filemanager.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicInstitutionRepository extends JpaRepository<PublicInstitution, Long> {
    PublicInstitution findByUniqueIdentification(String uniqueIdentification);
}

