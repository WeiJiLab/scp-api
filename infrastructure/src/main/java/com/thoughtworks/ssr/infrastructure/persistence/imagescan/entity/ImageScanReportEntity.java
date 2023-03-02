//package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldNameConstants;
//import org.springframework.web.multipart.MultipartFile;
//
//@Table(name = "image_scan_report")
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@EqualsAndHashCode
//@FieldNameConstants
//public class ImageScanReportEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "fileName")
//    private String fileName;
//
//    @Column(name = "scanReport")
//    private MultipartFile scanReport;
//
//    @Column(name = "createAt")
//    private String createAt;
//}
