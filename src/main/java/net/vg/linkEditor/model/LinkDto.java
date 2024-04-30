package net.vg.linkEditor.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class LinkDto {

    @NotEmpty(message = "This field is mandatory.")
    private String name;

    @NotEmpty(message = "This field is mandatory.")
    private String url;

    @NotEmpty(message = "This field is mandatory.")
    @Size(max = 2000, message = "The description cannot exceed 2000 characters.")
    private String description;

    @NotEmpty(message = "This field is mandatory.")
    private boolean isAvailableChrome;

    @NotEmpty(message = "This field is mandatory.")
    private boolean isAvailableFirefox;

    @NotEmpty(message = "This field is mandatory.")
    private boolean isActive;

    @NotEmpty(message = "This field is mandatory.")
    private MultipartFile imageFile;
}