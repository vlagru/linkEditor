package net.vg.linkEditor.service;

import net.vg.linkEditor.model.Link;
import net.vg.linkEditor.model.LinkDto;

import java.util.List;

public interface LinkEditorService {

    void createLink(LinkDto linkDto);

    List<Link> getAllLinks();

    void updateLink(LinkDto linkDto, Long id);

    void deleteLink(long id);

    List<Link> getAllChromeAvailable();

    List<Link>getAllFirefoxAvailable();

    List<Link> getAllActive();
}
