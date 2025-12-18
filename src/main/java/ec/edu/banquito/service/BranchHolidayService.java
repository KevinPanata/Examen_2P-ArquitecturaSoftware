package ec.edu.banquito.service;

import ec.edu.banquito.dto.request.BranchHolidayCreateListRequest;
import ec.edu.banquito.dto.request.BranchHolidayCreateRequest;
import ec.edu.banquito.dto.response.BranchHolidayResponse;
import ec.edu.banquito.exception.BadRequestException;
import ec.edu.banquito.exception.NotFoundException;
import ec.edu.banquito.model.Branch;
import ec.edu.banquito.model.BranchHoliday;
import ec.edu.banquito.repository.BranchHolidayRepository;
import ec.edu.banquito.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class BranchHolidayService {

    private static final Logger log = LoggerFactory.getLogger(BranchHolidayService.class);

    private final BranchRepository branchRepository;
    private final BranchHolidayRepository branchHolidayRepository;

    public BranchHolidayService(BranchRepository branchRepository, BranchHolidayRepository branchHolidayRepository) {
        this.branchRepository = branchRepository;
        this.branchHolidayRepository = branchHolidayRepository;
    }

    public List<BranchHolidayResponse> createHolidays(String branchId, BranchHolidayCreateListRequest request) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch '" + branchId + "' not found."));

        for (BranchHolidayCreateRequest h : request.getHolidays()) {
            if (branchHolidayRepository.existsByBranchIdAndDate(branchId, h.getDate())) {
                throw new BadRequestException("Holiday already exists for branch '" + branchId + "' and date '" + h.getDate() + "'.");
            }
        }

        List<BranchHoliday> saved = request.getHolidays().stream().map(h -> {
            BranchHoliday holiday = new BranchHoliday();
            holiday.setId(UUID.randomUUID().toString());
            holiday.setBranchId(branch.getId());
            holiday.setDate(h.getDate());
            holiday.setName(h.getName());
            return holiday;
        }).map(branchHolidayRepository::save).toList();

        log.info("Holidays created for branch {}: {}", branchId, saved.size());

        return saved.stream()
                .sorted(Comparator.comparing(BranchHoliday::getDate))
                .map(h -> new BranchHolidayResponse(h.getDate(), h.getName()))
                .toList();
    }

    public void deleteHoliday(String branchId, LocalDate date) {
        branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch '" + branchId + "' not found."));

        BranchHoliday holiday = branchHolidayRepository.findByBranchIdAndDate(branchId, date)
                .orElseThrow(() -> new NotFoundException("Holiday not found for branch '" + branchId + "' and date '" + date + "'."));

        branchHolidayRepository.deleteById(holiday.getId());
        log.info("Holiday deleted for branch {} date {}", branchId, date);
    }

    public List<BranchHolidayResponse> getHolidays(String branchId) {
        branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch '" + branchId + "' not found."));

        return branchHolidayRepository.findByBranchId(branchId).stream()
                .sorted(Comparator.comparing(BranchHoliday::getDate))
                .map(h -> new BranchHolidayResponse(h.getDate(), h.getName()))
                .toList();
    }

    public BranchHolidayResponse verifyHoliday(String branchId, LocalDate date) {
        branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch '" + branchId + "' not found."));

        BranchHoliday holiday = branchHolidayRepository.findByBranchIdAndDate(branchId, date)
                .orElseThrow(() -> new NotFoundException("Date '" + date + "' is not a holiday for branch '" + branchId + "'."));

        return new BranchHolidayResponse(holiday.getDate(), holiday.getName());
    }
}