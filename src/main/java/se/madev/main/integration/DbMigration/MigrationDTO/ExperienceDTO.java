package se.madev.main.integration.DbMigration.MigrationDTO;

/**
 * DTO class which olds data of a certain experience from a person in old database
 */
public class ExperienceDTO {

    private int competenceID;
    private int yearsofExp;

    public ExperienceDTO(int competenceID, int yearsofExp){
        this.competenceID = competenceID;
        this.yearsofExp = yearsofExp;
    }

    public int getCompetenceID() {
        return competenceID;
    }

    public int getYearsofExp() {
        return yearsofExp;
    }
}
