package se.madev.main.integration.DbMigration;

import org.springframework.beans.factory.annotation.Autowired;
import se.madev.main.integration.DbMigration.MigrationDTO.AvailabilityDTO;
import se.madev.main.integration.DbMigration.MigrationDTO.ExperienceDTO;
import se.madev.main.integration.DbMigration.MigrationDTO.UserDTO;
import se.madev.main.integration.RoleRepository;
import se.madev.main.model.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class that handles connection and extraction from an older Database
 */
public class OldDbHandler  {

    @Autowired
    RoleRepository roleRepository;

        private static final String POSTGGRES_DB_URI = "postgres://tbtviuwnibrvds:ecf3b280ba81ee9afd1ade344a99df8938bc61d0d0c22ffa3f0d41a20be5b849@ec2-52-86-33-50.compute-1.amazonaws.com:5432/d1ujd0fhtpvdss";
        private static final String POSTGRES_DB_USERNAME = "tbtviuwnibrvds";
        private static final String POSTGRES_DB_PASSWORD = "ecf3b280ba81ee9afd1ade344a99df8938bc61d0d0c22ffa3f0d41a20be5b849";
        private static final String POSTGRES_DB_DRIVER = "org.postgresql.Driver";
        private Connection dbConnection;

        public OldDbHandler() throws URISyntaxException, SQLException {
            this.dbConnection = getConnection();
            System.err.println("OLD DB HANDLER CREATED");
        }

    /**
     * Returns a connection to the old database
     * @return
     * @throws URISyntaxException
     * @throws SQLException
     */
        public static Connection getConnection() throws URISyntaxException, SQLException {

            URI oldDbURI = new URI(POSTGGRES_DB_URI);
            String oldDbURL = "jdbc:postgresql://" +oldDbURI.getHost() +oldDbURI.getPath();

            return DriverManager.getConnection(oldDbURL, POSTGRES_DB_USERNAME, POSTGRES_DB_PASSWORD);
        }

    /**
     * Extracts all information from table persons from old database
     * @return ArrayList of UserDTO which holds data of all old persons.
     * @throws SQLException
     */
    public ArrayList<UserDTO> extractUsers() throws SQLException{
            ArrayList<UserDTO> oldUsers = new ArrayList<>();
            Statement stmt = this.dbConnection.createStatement();
            String query = "Select * from person";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                UserDTO oldUser = new UserDTO(result.getInt("person_id"), result.getString("name"), result.getString("surname"), result.getString("ssn"), result.getString("email"), result.getString("password"), result.getInt("role_id"), result.getString("username"));
                oldUsers.add(oldUser);
            }

            return oldUsers;
        }

    /**
     * Extracts all data from old dB
     * @return
     * @throws SQLException
     */
    public ArrayList<Competence> extractCompetence() throws SQLException {
            ArrayList<Competence> oldCompetences = new ArrayList<>();
            Statement stmt =  this.dbConnection.createStatement();
            String query = "Select * from competence";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                Competence oldCompetence = new Competence();
                oldCompetence.setId(result.getInt("competence_id"));
                oldCompetence.setName(result.getString("name"));
                oldCompetences.add(oldCompetence);
            }
            return oldCompetences;
        }

    /**
     * Extracts all Experience connected to a specific persons ID from old Database
     * @param personId
     * @return ArrayList of Experience connected to a specific person stored in ExperienceDTOs
     * @throws SQLException
     */
        public ArrayList<ExperienceDTO> extractExperience(int personId) throws SQLException{
            ArrayList<ExperienceDTO> oldExperiences = new ArrayList<>();
            String query = "Select competence_id, years_of_experience FROM competence_profile WHERE person_id = ?";
            try{
                PreparedStatement preparedStmt = this.dbConnection.prepareStatement(query);
                preparedStmt.setInt(1, personId);
                ResultSet result = preparedStmt.executeQuery();
                while(result.next()){
                    int competenceID = result.getInt("competence_id");
                    int yearsOfExperience = result.getInt("years_of_experience");
                    ExperienceDTO oldExperience = new ExperienceDTO(competenceID, yearsOfExperience);
                    oldExperiences.add(oldExperience);
                    return oldExperiences;
                }
            } catch(Exception e){ System.out.println(e); }
            return null;
        }

    /**
     * Extracts all Availabilities connected to a specific person from old database
     * @param personId
     * @return ArrayList containing all Availabilities connected to a specific person stored in AvailabilityDTOs
     * @throws SQLException
     */
        public ArrayList<AvailabilityDTO> extractAvailability(int personId) throws SQLException{
            ArrayList<AvailabilityDTO> oldAvailabilities = new ArrayList<>();
            String query = "Select from_date, to_date FROM availability WHERE person_id = ?";
            try{
                PreparedStatement preparedStmt = this.dbConnection.prepareStatement(query);
                preparedStmt.setInt(1, personId);
                ResultSet result = preparedStmt.executeQuery();
                while(result.next()){
                    Date fromDate = result.getDate("from_date");
                    Date toDate = result.getDate("to_date");
                    AvailabilityDTO oldAvailability = new AvailabilityDTO(fromDate, toDate);
                    oldAvailabilities.add(oldAvailability);
                }
                return oldAvailabilities;
            }catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
}
