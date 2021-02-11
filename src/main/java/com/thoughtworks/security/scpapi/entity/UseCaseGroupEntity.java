package com.thoughtworks.security.scpapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "use_case_group")
@Builder
public class UseCaseGroupEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

//    @ManyToMany(targetEntity = UseCaseEntity.class, fetch = FetchType.LAZY)
//    @JoinTable(name = "use_case_group_link",
//            //joinColumns,当前对象在中间表中的外键
//            joinColumns = {@JoinColumn(name = "use_case_group_id", referencedColumnName = "id")},
//            //inverseJoinColumns，对方对象在中间表的外键
//            inverseJoinColumns = {@JoinColumn(name = "use_case_id", referencedColumnName = "id")})

//    private List<UseCaseEntity> useCases = new ArrayList<>();

}
