package awp.jpadb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import awp.jpadb.data.School;

/**
 * This repository only for the basic school find, save etc.
 */

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
    
}
