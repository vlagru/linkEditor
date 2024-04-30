package net.vg.linkEditor.service;

import lombok.extern.slf4j.Slf4j;
import net.vg.linkEditor.exception.ResourceNotFoundException;
import net.vg.linkEditor.model.Link;
import net.vg.linkEditor.model.LinkDto;
import net.vg.linkEditor.repository.LinkEditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class LinkEditorServiceImpl implements LinkEditorService {

    @Autowired
    LinkEditorRepository linkEditorRepository;


    @Override
    public void createLink(LinkDto linkDto) {

        MultipartFile image = linkDto.getImageFile();
        Date creationDate = new Date();
        String storageFileName = creationDate.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        Link link = new Link();
        link.setUrl(linkDto.getUrl());
        link.setName(linkDto.getName());
        link.setDescription(linkDto.getDescription());
        link.setIsActive(linkDto.isActive());
        link.setIsAvailableChrome(linkDto.isAvailableChrome());
        link.setIsAvailableFirefox(linkDto.isAvailableFirefox());
        link.setImageFileName(storageFileName);

        linkEditorRepository.save(link);

        log.info("Link with id " + link.getId() + "has been created.");
    }

    @Override
    public List<Link> getAllLinks() {
        return linkEditorRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public void updateLink(LinkDto linkDto, Long id) {

        var linkToBeUpdated = linkEditorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Link with id: " + id + " does not exist."
        ));

        if (!linkDto.getImageFile().isEmpty()) {
            String uploadDir = "public/images/";
            Path oldImagePath = Paths.get(uploadDir + linkToBeUpdated.getImageFileName());

            try {
                Files.delete(oldImagePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MultipartFile image = linkDto.getImageFile();
            Date creationDate = new Date();
            String storageFileName = creationDate.getTime() + "_" + image.getOriginalFilename();


            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
            linkToBeUpdated.setImageFileName(storageFileName);
        }
        linkToBeUpdated.setUrl(linkDto.getUrl());
        linkToBeUpdated.setName(linkDto.getName());
        linkToBeUpdated.setDescription(linkDto.getDescription());
        linkToBeUpdated.setIsActive(linkDto.isActive());
        linkToBeUpdated.setIsAvailableChrome(linkDto.isAvailableChrome());
        linkToBeUpdated.setIsAvailableFirefox(linkDto.isAvailableFirefox());

        linkEditorRepository.save(linkToBeUpdated);

        log.info("Link with id " + id + "has been updated.");
    }

    @Override
    public void deleteLink(long id) {
        var linkToBeDeleted = linkEditorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Link with id: " + id + " does not exist."));

        linkEditorRepository.delete(linkToBeDeleted);

        log.info("Link with id " + id + "has been deleted.");
    }

    @Override
    public List<Link> getAllChromeAvailable() {
        return linkEditorRepository.findByIsActiveAndIsAvailableChrome(true, true);
    }

    @Override
    public List<Link> getAllFirefoxAvailable() {
        return linkEditorRepository.findByIsActiveAndIsAvailableFirefox(true, true);
    }

    @Override
    public List<Link> getAllActive() {
        return linkEditorRepository.findByIsActive(true);
    }


}
