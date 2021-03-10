package ch.awae.mytools.datastore.model;

import ch.awae.mytools.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatastoreEntryRepository extends JpaRepository<DatastoreEntry, Long> {

    Optional<DatastoreEntry> findByIdAndUser(long id, User user);

    List<DatastoreEntry> findByUserAndCategory(User user, String category);

}
