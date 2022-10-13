package com.kerjahubs.userservice.entity.lowongan;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="LOWONGAN_REQUIREMENT")
@NoArgsConstructor
public class LowonganRequirement {
    @Id
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "name")
    private String name = DefaultValues.emptyString;
    @Column(name = "sequence", length = 5)
    private int sequence = DefaultValues.emptyInteger;
    @Column(name = "lowonganId", length = 36)
    private String lowonganId = DefaultValues.emptyString;
}
