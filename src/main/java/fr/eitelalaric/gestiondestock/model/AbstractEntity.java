package fr.eitelalaric.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @CreatedDate
    @Column(name = "creationDate" , nullable=false )
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastUpdateDate")
    @JsonIgnore
    private Instant lastUpdateDate;

}
