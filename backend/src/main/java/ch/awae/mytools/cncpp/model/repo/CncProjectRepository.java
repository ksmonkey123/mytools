package ch.awae.mytools.cncpp.model.repo;

import ch.awae.mytools.cncpp.model.CncProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CncProjectRepository extends JpaRepository<CncProject, Long> {
}
