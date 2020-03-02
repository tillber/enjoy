package se.madev.main.integration.DbMigration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.madev.main.integration.*;
import se.madev.main.integration.DbMigration.MigrationDTO.AvailabilityDTO;
import se.madev.main.integration.DbMigration.MigrationDTO.ExperienceDTO;
import se.madev.main.integration.DbMigration.MigrationDTO.UserDTO;
import se.madev.main.integration.DbMigration.OldDbHandler;
import se.madev.main.model.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import se.madev.main.integration.RoleRepository;
import se.madev.main.integration.UserRepository;

/**
 * Class that handles migration of data from an old database to the new one.
 */
@Service
public class MigrationHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    CompetenceRepository competenceRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    StatusRepository statusRepository;


    private static final String POSTGGRES_DB_URI = "postgres://lhvcyjeplgfygh:203d7fabbd994e37bbd6d5bde2df5992a1ba88ecce9fd75523e4a4b8f10ef019@ec2-46-51-190-87.eu-west-1.compute.amazonaws.com:5432/da1butricg032i";
    private static final String POSTGRES_DB_USERNAME = "lhvcyjeplgfygh";
    private static final String POSTGRES_DB_PASSWORD = "203d7fabbd994e37bbd6d5bde2df5992a1ba88ecce9fd75523e4a4b8f10ef019";
    private static final String POSTGRES_DB_DRIVER = "org.postgresql.Driver";
    private OldDbHandler oldDbHandler;
    private Connection newDbConnection;

    private PasswordEncoder passwordEncoder;

    private int migrationCounter;

    /**
     * Constructor that takes all repositories and encoder to ensure that the right context is applied.
     * @param roleRepository
     * @param applicationRepository
     * @param statusRepository
     * @param userRepository
     * @param competenceRepository
     * @param encoder
     * @throws URISyntaxException
     * @throws SQLException
     */
    public  MigrationHandler(RoleRepository roleRepository, ApplicationRepository applicationRepository, StatusRepository statusRepository, UserRepository userRepository, CompetenceRepository competenceRepository, PasswordEncoder encoder) throws URISyntaxException, SQLException {
        System.err.println("MIGRATION HANDLER CREATED");
        this.roleRepository = roleRepository;
        this.applicationRepository = applicationRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.competenceRepository = competenceRepository;
        this.oldDbHandler = new OldDbHandler();
        this.newDbConnection = getNewConnection();
        this.passwordEncoder = encoder;
        this.migrationCounter = 1000;
    }

    /**
     * Creates a new connection to a database
     * @return Connection to new database
     * @throws SQLException
     * @throws URISyntaxException
     */
    public Connection getNewConnection() throws SQLException, URISyntaxException {

        URI oldDbURI = new URI(POSTGGRES_DB_URI);
        String oldDbURL = "jdbc:postgresql://" +oldDbURI.getHost() +oldDbURI.getPath();

        return DriverManager.getConnection(oldDbURL, POSTGRES_DB_USERNAME, POSTGRES_DB_PASSWORD);
    }

    /**
     * Function that extracts all critical data from old database, transforms and organizes the data to fit the new database.
     * @throws SQLException
     * @throws URISyntaxException
     */
    public void Migrate() throws SQLException, URISyntaxException {
        System.err.println("MIGRATE FUNCTION ENTERED");


        ArrayList<UserDTO> oldUsers = oldDbHandler.extractUsers();
        String query = "INSERT INTO users (firstname, lastname, email, username, password, dateofbirth, role)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        System.err.println("OLD USERS QUERIED");
        for (UserDTO oldUser : oldUsers) {
            System.err.println("KOMMER VI IN I ITERATOR?");
            if(oldUser.getRoleId() == 1){
                PreparedStatement preparedStmt = this.newDbConnection.prepareStatement(query);
                preparedStmt.setString(1, oldUser.getName());
                preparedStmt.setString(2, oldUser.getSurname());
                preparedStmt.setString(3, oldUser.getEmail());
                preparedStmt.setString(4, oldUser.getUsername());
                preparedStmt.setString(5, passwordEncoder.encode(oldUser.getPassword()));
                if(!(oldUser.getSsn() == null)) {
                    preparedStmt.setDate(6, java.sql.Date.valueOf(dateString(oldUser.getSsn())));
                }else{
                    preparedStmt.setDate(6, new java.sql.Date(01012020));
                }
                preparedStmt.setInt(7, oldUser.getRoleId());
                preparedStmt.executeUpdate();
                User migratedUser = userRepository.findByUsername(oldUser.getUsername());
                System.out.println(migratedUser.toString());
            } else if(oldUser.getRoleId() == 2){
                PreparedStatement preparedStmt = this.newDbConnection.prepareStatement(query);
                preparedStmt.setString(1, oldUser.getName());
                preparedStmt.setString(2, oldUser.getSurname());
                preparedStmt.setString(3, oldUser.getEmail());
                preparedStmt.setString(4, oldUser.getUsername());
                preparedStmt.setString(5, passwordEncoder.encode(oldUser.getPassword()));

                if(!(oldUser.getSsn() == null)) {
                    preparedStmt.setDate(6, java.sql.Date.valueOf(dateString(oldUser.getSsn())));
                }else{
                    preparedStmt.setDate(6, new java.sql.Date(01012020));
                }
                preparedStmt.setInt(7, oldUser.getRoleId());
                preparedStmt.executeUpdate();
                User migratedUser = userRepository.findByUsername(oldUser.getUsername());
                System.out.println(migratedUser.toString());

                Application application = createApplication(migratedUser);
                attachAvailability(oldUser, application);
                attachExperience(oldUser, application);
            }
        }
    }


    /**
     * Creates a new application based on an old user from old database
     * @param migratedUser
     * @return
     */
    private Application createApplication(User migratedUser){
        Application app = new Application(migratedUser);
        Status status = statusRepository.findById(1);
        app.setStatus(status);
        applicationRepository.save(app);
        Application application = applicationRepository.findByApplicant(migratedUser);
        return application;
    }

    /**
     * Connect availability to a application created to host an old user from the older database.
     * @param oldUser
     * @param application
     * @throws SQLException
     */
    private void attachAvailability(UserDTO oldUser, Application application) throws SQLException {
        ArrayList<AvailabilityDTO> oldAvailabilities = oldDbHandler.extractAvailability(oldUser.getOldId());
        for(AvailabilityDTO oldAvailability : oldAvailabilities){
            Date fromdate = oldAvailability.getFromDate();
            Date todate = oldAvailability.getToDate();
            Availability availability = new Availability(application, fromdate, todate);
            application.setAvailability(availability);
            applicationRepository.save(application);
            System.err.println(application.toString());
        }
    }

    /**
     * Connects experience to a application created to host an old user from the older database.
     * @param oldUser
     * @param application
     * @throws SQLException
     */
    private void attachExperience(UserDTO oldUser, Application application) throws SQLException {
        ArrayList<ExperienceDTO> oldExperiences = oldDbHandler.extractExperience(oldUser.getOldId());
        for(ExperienceDTO oldExperience : oldExperiences){
            int competenceID = oldExperience.getCompetenceID();
            int yearsOfExperience = oldExperience.getYearsofExp();
            Competence competence = competenceRepository.findById(competenceID);
            Experience experience = new Experience(application, competence, yearsOfExperience);
            application.setExperience(experience);
            applicationRepository.save(application);
            System.err.println(application.toString());
        }
    }

    /**
     * Converts old Social security string into new date string that better suit the new database
     * @param ssn
     * @return
     */
    private String dateString(String ssn){
        String[] stringSplit = ssn.split("-");
        ssn = stringSplit[0];
        String year = ssn.substring(0,4);
        String month = ssn.substring(4,6);
        String day = ssn.substring(6,8);
        String date = year +"-" +month +"-" +day;
        return date;
    }

}
