package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.madev.main.model.Availability;

import java.util.ArrayList;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    ArrayList<Availability> findByApplicationId(int applicationID);
}
