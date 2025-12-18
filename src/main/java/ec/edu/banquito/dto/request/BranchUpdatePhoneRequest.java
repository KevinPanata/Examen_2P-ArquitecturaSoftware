package ec.edu.banquito.dto.request;

import jakarta.validation.constraints.NotBlank;

public class BranchUpdatePhoneRequest {

    @NotBlank
    private String phoneNumber;

    public BranchUpdatePhoneRequest() {
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}