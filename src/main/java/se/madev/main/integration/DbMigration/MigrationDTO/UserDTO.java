package se.madev.main.integration.DbMigration.MigrationDTO;

/**
 * DTO which stores information about a person in the old database
 */
public class UserDTO {

    private int oldId;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private String password;
    private int roleId;
    private String username;

    public UserDTO(int oldId, String name, String surname, String ssn, String email, String password, int roleId, String username){
        this.oldId = oldId;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getOldId() {
        return oldId;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
