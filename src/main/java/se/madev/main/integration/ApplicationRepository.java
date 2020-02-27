package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.madev.main.model.Application;
import se.madev.main.model.Status;
import se.madev.main.model.User;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    Application findByApplicant(User applicant);
    
    Application existsByApplicant(User applicant);
    
    int countByStatus(Status status);
}
