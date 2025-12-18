package ec.edu.banquito.dto.request;

import ec.edu.banquito.model.BranchState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BranchCreateRequest {

    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String emailAddress;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private BranchState state;

    public BranchCreateRequest() {
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
}