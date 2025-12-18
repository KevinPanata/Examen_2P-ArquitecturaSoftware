package ec.edu.banquito.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BranchHolidayCreateRequest {

    @NotNull
    private LocalDate date;

    @NotBlank
    private String name;

    public BranchHolidayCreateRequest() {
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}