package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.madev.main.model.Status;
import se.madev.main.model.Status.Type;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findById(int id);
}
