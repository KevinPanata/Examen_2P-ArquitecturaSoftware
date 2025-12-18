package ec.edu.banquito.dto.response;

import ec.edu.banquito.model.BranchState;

import java.time.OffsetDateTime;
import java.util.List;

public class BranchResponse {

    private String id;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private BranchState state;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModifiedDate;

    private List<BranchHolidayResponse> branchHolidays;

    public BranchResponse() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public BranchState getState() { return state; }
    public void setState(BranchState state) { this.state = state; }

    public OffsetDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(OffsetDateTime creationDate) { this.creationDate = creationDate; }

    public OffsetDateTime getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(OffsetDateTime lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }

    public List<BranchHolidayResponse> getBranchHolidays() { return branchHolidays; }
    public void setBranchHolidays(List<BranchHolidayResponse> branchHolidays) { this.branchHolidays = branchHolidays; }
}