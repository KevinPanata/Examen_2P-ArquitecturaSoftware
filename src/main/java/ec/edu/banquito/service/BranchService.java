package ec.edu.banquito.service;

import ec.edu.banquito.dto.request.BranchCreateRequest;
import ec.edu.banquito.dto.request.BranchUpdatePhoneRequest;
import ec.edu.banquito.dto.response.BranchHolidayResponse;
import ec.edu.banquito.dto.response.BranchResponse;
import ec.edu.banquito.dto.response.PageResponse;
import ec.edu.banquito.exception.BadRequestException;
import ec.edu.banquito.exception.NotFoundException;
import ec.edu.banquito.model.Branch;
import ec.edu.banquito.model.BranchHoliday;
import ec.edu.banquito.repository.BranchHolidayRepository;
import ec.edu.banquito.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class BranchService {

    private static final Logger log = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepository;
    private final BranchHolidayRepository branchHolidayRepository;

    public BranchService(BranchRepository branchRepository, BranchHolidayRepository branchHolidayRepository) {
        this.branchRepository = branchRepository;
        this.branchHolidayRepository = branchHolidayRepository;
    }

    public PageResponse<BranchResponse> getAllBranches(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());
        Page<Branch> branches = branchRepository.findAll(pageable);

        List<BranchResponse> content = branches.getContent().stream()
                .map(this::toBranchResponseWithHolidays)
                .toList();

        return new PageResponse<>(
                content,
                branches.getNumber(),
                branches.getSize(),
                branches.getTotalElements(),
                branches.getTotalPages()
        );
    }

    public BranchResponse createBranch(BranchCreateRequest request) {
        if (branchRepository.existsById(request.getId())) {
            throw new BadRequestException("Branch with id '" + request.getId() + "' already exists.");
        }

        OffsetDateTime now = OffsetDateTime.now();

        Branch branch = new Branch();
        branch.setId(request.getId());
        branch.setEmailAddress(request.getEmailAddress());
        branch.setName(request.getName());
        branch.setPhoneNumber(request.getPhoneNumber());
        branch.setState(request.getState());
        branch.setCreationDate(now);
        branch.setLastModifiedDate(now);

        Branch saved = branchRepository.save(branch);

        log.info("Branch created: {}", saved.getId());
        return toBranchResponseWithHolidays(saved);
    }

    public BranchResponse getBranchById(String branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch '" + branchId + "' not found."));
        return toBranchResponseWithHolidays(branch);
    }

    public BranchResponse updateBranchPhone(String branchId, BranchUpdatePhoneRequest request) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch '" + branchId + "' not found."));

        branch.setPhoneNumber(request.getPhoneNumber());
        branch.setLastModifiedDate(OffsetDateTime.now());

        Branch saved = branchRepository.save(branch);

        log.info("Branch updated (phoneNumber): {}", saved.getId());
        return toBranchResponseWithHolidays(saved);
    }

    private BranchResponse toBranchResponseWithHolidays(Branch branch) {
        List<BranchHoliday> holidays = branchHolidayRepository.findByBranchId(branch.getId());
        List<BranchHolidayResponse> holidayResponses = holidays.stream()
                .sorted(Comparator.comparing(BranchHoliday::getDate))
                .map(h -> new BranchHolidayResponse(h.getDate(), h.getName()))
                .toList();

        BranchResponse response = new BranchResponse();
        response.setId(branch.getId());
        response.setEmailAddress(branch.getEmailAddress());
        response.setName(branch.getName());
        response.setPhoneNumber(branch.getPhoneNumber());
        response.setState(branch.getState());
        response.setCreationDate(branch.getCreationDate());
        response.setLastModifiedDate(branch.getLastModifiedDate());
        response.setBranchHolidays(holidayResponses);
        return response;
    }
}