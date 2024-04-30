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

@RestController
@RequestMapping("/api/linkEditor")
@Slf4j
public class LinkEditorController {

    @Autowired
    LinkEditorServiceImpl linkEditorServiceImpl;

    @GetMapping("/getAllLinks")
    public ResponseEntity<List<Link>> getAllLinks() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllLinks()
        );
    }

    @GetMapping("/getAllChromeAvailable")
    public ResponseEntity<List<Link>> getAllChromeAvailable() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllChromeAvailable()
        );
    }

    @GetMapping("/getAllFirefoxAvailable")
    public ResponseEntity<List<Link>> getAllFirefoxAvailable() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllFirefoxAvailable()
        );
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<List<Link>> getAllActive() {
        return ResponseEntity.ok(
                linkEditorServiceImpl.getAllActive()
        );
    }

    @PostMapping(value = "/createLink")
    public void createLink(@Valid @ModelAttribute LinkDto linkDto, BindingResult result) {

        if (linkDto.getImageFile().isEmpty()){
            result.addError(new FieldError("linkDto", "imageFile", "The image file is required"));
        }

        linkEditorServiceImpl.createLink(linkDto);

    }

    @PutMapping("/{id}")
    public void updateLink(@PathVariable long id, @Valid @ModelAttribute LinkDto linkDto, BindingResult result) {

        linkEditorServiceImpl.updateLink(linkDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLink(@PathVariable long id){

        linkEditorServiceImpl.deleteLink(id);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
