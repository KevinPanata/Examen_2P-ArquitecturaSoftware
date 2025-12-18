package ec.edu.banquito.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BranchHolidayCreateListRequest {

    @NotEmpty
    @Valid
    private List<BranchHolidayCreateRequest> holidays;

    public BranchHolidayCreateListRequest() {
    }

    public List<BranchHolidayCreateRequest> getHolidays() { return holidays; }
    public void setHolidays(List<BranchHolidayCreateRequest> holidays) { this.holidays = holidays; }
}