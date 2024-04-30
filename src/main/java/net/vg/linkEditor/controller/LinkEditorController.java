package net.vg.linkEditor.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.vg.linkEditor.model.Link;
import net.vg.linkEditor.model.LinkDto;
import net.vg.linkEditor.service.LinkEditorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vladimir Grulich
 * Controller layer containing CRUD operations.
 */
@RestController
@RequestMapping("/api/linkEditor")
@Slf4j
public class LinkEditorController {

    @Autowired
    LinkEditorServiceImpl linkEditorServiceImpl;

    /**
     * Get all links from the database.
     * @return List<Link>
     */
    @GetMapping("/getAllLinks")
    public ResponseEntity<List<Link>> getAllLinks() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllLinks()
        );
    }

    /**
     * Get all links with the attribute 'isAvailableChrome' set on true.
     * @return List<Link>
     */
    @GetMapping("/getAllChromeAvailable")
    public ResponseEntity<List<Link>> getAllChromeAvailable() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllChromeAvailable()
        );
    }

    /**
     * Get all links with the attribute 'isAvailableFirefox' set on true.
     * @return List<Link>
     */
    @GetMapping("/getAllFirefoxAvailable")
    public ResponseEntity<List<Link>> getAllFirefoxAvailable() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllFirefoxAvailable()
        );
    }

    /**
     * Get all links with the attribute 'active' set on true.
     * @return List<Link>
     */
    @GetMapping("/getAllActive")
    public ResponseEntity<List<Link>> getAllActive() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllActive()
        );
    }

    /**
     * Create a link where there is a multipart file - picture - and JSON body in one object.
     * @return void
     */
    @PostMapping(value = "/createLink")
    public void createLink(@Valid @ModelAttribute LinkDto linkDto, BindingResult result) {

        if (linkDto.getImageFile().isEmpty()){
            result.addError(new FieldError("linkDto", "imageFile", "The image file is required"));
        }

        linkEditorServiceImpl.createLink(linkDto);

    }

    /**
     * Update a link on the basis of its id.
     * @return void
     */
    @PutMapping("/{id}")
    public void updateLink(@PathVariable long id, @Valid @ModelAttribute LinkDto linkDto, BindingResult result) {

        linkEditorServiceImpl.updateLink(linkDto, id);
    }

    /**
     * Delete a link on the basis of its id.
     * @return HttpStatus
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLink(@PathVariable long id){

        linkEditorServiceImpl.deleteLink(id);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
