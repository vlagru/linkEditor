package net.vg.linkEditor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "link_editor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    @Column
    private String url;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "IS_AVAILABLE_IN_CHROME")
    private Boolean isAvailableChrome;
    @Column(name = "IS_AVAILABLE_IN_FIREFOX")
    private Boolean isAvailableFirefox;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IMAGE_FILE_NAME")
    private String imageFileName;
}
