package ec.edu.banquito.repository;

import ec.edu.banquito.model.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<Branch, String> {
}