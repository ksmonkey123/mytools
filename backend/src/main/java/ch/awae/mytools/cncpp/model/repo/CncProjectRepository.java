package ch.awae.mytools.cncpp.model.repo;

import ch.awae.mytools.cncpp.model.CncProject;
import ch.awae.mytools.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CncProjectRepository extends JpaRepository<CncProject, Long> {

    Stream<CncProject> findByUser(User currentUser);

    Optional<CncProject> findByIdAndUser(long projectId, User currentUser);

}
