package net.vg.linkEditor.repository;

import net.vg.linkEditor.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkEditorRepository extends JpaRepository<Link, Long> {

    List<Link> findByIsActive(boolean isActive);

    List<Link> findByIsActiveAndIsAvailableChrome (boolean isActive, boolean isAvailableChrome);

    List<Link> findByIsActiveAndIsAvailableFirefox (boolean isActive, boolean isAvailableFirefox);

}
