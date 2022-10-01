package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="DOCUMENT_TYPE")
@NoArgsConstructor
public class DocumentType implements Serializable {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "name", nullable = false, length = 100)
    private String name = DefaultValues.emptyString;
}
