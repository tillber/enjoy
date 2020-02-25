package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.madev.main.model.Applications;
import se.madev.main.model.User;

@Repository
public interface ApplicationRepository extends JpaRepository<Applications, Integer> {

    Applications findByApplicant(User applicant);
}
