package net.vg.linkEditor.repository;

import net.vg.linkEditor.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Vladimir Grulich
 * Repository layer.
 */
@Repository
public interface LinkEditorRepository extends JpaRepository<Link, Long> {

    /**
     * Find all the links in the database which have attribute 'active' set on true.
     * @param isActive
     * @return List<Link>
     */
    List<Link> findByIsActive(boolean isActive);

    /**
     * Find all the links in the database which have attributes 'active' and 'isAvailableChrome' set on true.
     * @param isActive
     * @return List<Link>
     */
    List<Link> findByIsActiveAndIsAvailableChrome (boolean isActive, boolean isAvailableChrome);

    /**
     * Find all the links in the database which have attributes 'active' and 'isAvailableFirefox' set on true.
     * @param isActive
     * @return List<Link>
     */
    List<Link> findByIsActiveAndIsAvailableFirefox (boolean isActive, boolean isAvailableFirefox);

}
