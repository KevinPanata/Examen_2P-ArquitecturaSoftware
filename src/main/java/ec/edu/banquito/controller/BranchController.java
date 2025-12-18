package ec.edu.banquito.controller;


import ec.edu.banquito.dto.request.BranchCreateRequest;
import ec.edu.banquito.dto.request.BranchUpdatePhoneRequest;
import ec.edu.banquito.dto.response.BranchResponse;
import ec.edu.banquito.dto.response.PageResponse;
import ec.edu.banquito.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    private static final Logger log = LoggerFactory.getLogger(BranchController.class);

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Operation(summary = "Get all branches (paginated)")
    @GetMapping
    public ResponseEntity<PageResponse<BranchResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        log.info("GET /api/v1/branches?page={}&limit={}", page, limit);
        return ResponseEntity.ok(branchService.getAllBranches(page, limit));
    }

    @Operation(summary = "Create a branch (without holidays)")
    @PostMapping
    public ResponseEntity<BranchResponse> create(@Valid @RequestBody BranchCreateRequest request) {
        log.info("POST /api/v1/branches id={}", request.getId());
        BranchResponse created = branchService.createBranch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Get branch by id")
    @GetMapping("/{branchId}")
    public ResponseEntity<BranchResponse> getById(@PathVariable String branchId) {
        log.info("GET /api/v1/branches/{}", branchId);
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }

    @Operation(summary = "Update branch phoneNumber only")
    @PatchMapping("/{branchId}")
    public ResponseEntity<BranchResponse> updatePhone(
            @PathVariable String branchId,
            @Valid @RequestBody BranchUpdatePhoneRequest request
    ) {
        log.info("PATCH /api/v1/branches/{} phoneNumber={}", branchId, request.getPhoneNumber());
        return ResponseEntity.ok(branchService.updateBranchPhone(branchId, request));
    }
}