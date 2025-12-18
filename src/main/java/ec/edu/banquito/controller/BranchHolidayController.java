package ec.edu.banquito.controller;

import ec.edu.banquito.dto.request.BranchHolidayCreateListRequest;
import ec.edu.banquito.dto.response.BranchHolidayResponse;
import ec.edu.banquito.service.BranchHolidayService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branches/{branchId}/holidays")
public class BranchHolidayController {

    private static final Logger log = LoggerFactory.getLogger(BranchHolidayController.class);

    private final BranchHolidayService branchHolidayService;

    public BranchHolidayController(BranchHolidayService branchHolidayService) {
        this.branchHolidayService = branchHolidayService;
    }

    @Operation(summary = "Create holidays for a branch")
    @PostMapping
    public ResponseEntity<List<BranchHolidayResponse>> createHolidays(
            @PathVariable String branchId,
            @Valid @RequestBody BranchHolidayCreateListRequest request
    ) {
        log.info("POST /api/v1/branches/{}/holidays count={}", branchId, request.getHolidays().size());
        List<BranchHolidayResponse> created = branchHolidayService.createHolidays(branchId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Delete a holiday by date for a branch")
    @DeleteMapping("/{date}")
    public ResponseEntity<Void> deleteHoliday(
            @PathVariable String branchId,
            @PathVariable LocalDate date
    ) {
        log.info("DELETE /api/v1/branches/{}/holidays/{}", branchId, date);
        branchHolidayService.deleteHoliday(branchId, date);
        return ResponseEntity.noContent().build(); // 204
    }

    @Operation(summary = "Get all holidays of a branch")
    @GetMapping
    public ResponseEntity<List<BranchHolidayResponse>> getHolidays(@PathVariable String branchId) {
        log.info("GET /api/v1/branches/{}/holidays", branchId);
        return ResponseEntity.ok(branchHolidayService.getHolidays(branchId));
    }

    @Operation(summary = "Verify if a date is a holiday in a branch (200 if yes, 404 if no)")
    @GetMapping("/{date}")
    public ResponseEntity<BranchHolidayResponse> verifyHoliday(
            @PathVariable String branchId,
            @PathVariable LocalDate date
    ) {
        log.info("GET /api/v1/branches/{}/holidays/{}", branchId, date);
        return ResponseEntity.ok(branchHolidayService.verifyHoliday(branchId, date));
    }
}