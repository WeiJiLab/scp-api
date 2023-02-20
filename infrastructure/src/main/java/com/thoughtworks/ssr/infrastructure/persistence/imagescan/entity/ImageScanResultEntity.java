package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "image_scan_steps")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ImageScanResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pj_id;
    private enum type_option {
        SOURCE, BINARY, OTHER
    }
    private enum status {
        START, PROCESSING, END, ERROR
    }
    private enum step {
        PREPARE, COLLECTION, CHECK, REINFORCE, TEST
    }
    private enum stage {
        CHECK, REINFORCE, TEST
    }
    private String logs;
}
