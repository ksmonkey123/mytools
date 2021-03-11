package ch.awae.mytools.cncpp.model.repo;

import ch.awae.mytools.cncpp.model.CncProject;
import ch.awae.mytools.cncpp.model.CncRawGcodeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CncRawGcodeFileRepository extends JpaRepository<CncRawGcodeFile, Long> {

    List<CncRawGcodeFile> findByProjectOrderByProjectOrderAsc(CncProject project);

}
