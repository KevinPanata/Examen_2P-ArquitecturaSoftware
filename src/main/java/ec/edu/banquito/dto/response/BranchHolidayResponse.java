package ec.edu.banquito.dto.response;

import java.time.LocalDate;

public class BranchHolidayResponse {

    private LocalDate date;
    private String name;

    public BranchHolidayResponse() {
    }

    public BranchHolidayResponse(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}