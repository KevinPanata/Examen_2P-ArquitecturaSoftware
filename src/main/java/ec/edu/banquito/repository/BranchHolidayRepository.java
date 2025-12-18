package ec.edu.banquito.repository;

import ec.edu.banquito.model.BranchHoliday;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BranchHolidayRepository extends MongoRepository<BranchHoliday, String> {

    List<BranchHoliday> findByBranchId(String branchId);

    Optional<BranchHoliday> findByBranchIdAndDate(String branchId, LocalDate date);

    boolean existsByBranchIdAndDate(String branchId, LocalDate date);

    void deleteByBranchIdAndDate(String branchId, LocalDate date);
}