package se.madev.main.integration.DbMigration.MigrationDTO;

import java.util.Date;

/**
 * DTO class which stores data about a availability from old database.
 */
public class AvailabilityDTO {

    private Date fromDate;
    private Date toDate;

    public AvailabilityDTO(Date fromDate, Date toDate){
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }
}
