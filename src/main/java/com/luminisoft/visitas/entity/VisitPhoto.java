package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit_photos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //[cite: 1]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit; //[cite: 1]

    @Column(name = "original_name", nullable = false)
    private String originalName; //[cite: 1]

    @Column(name = "stored_name", nullable = false)
    private String storedName; //[cite: 1]

    @Column(nullable = false, length = 500)
    private String path; //[cite: 1]

    @Column(nullable = false, length = 10)
    private String extension; //[cite: 1]

    @Column(name = "mime_type", nullable = false, length = 100)
    private String mimeType; //[cite: 1]

    @Column(nullable = false)
    private Long size; //[cite: 1]

    @Column(name = "created_by", nullable = false)
    private Long createdBy; //[cite: 1]

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //[cite: 1]

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; //[cite: 1]

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
