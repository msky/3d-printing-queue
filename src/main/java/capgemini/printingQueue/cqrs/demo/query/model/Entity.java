package capgemini.printingQueue.cqrs.demo.query.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base Entity
 *
 * @author cem ikta
 */
@MappedSuperclass
@Data
@NoArgsConstructor
abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "3D_SEQUENCE")
    @SequenceGenerator(name = "3D_SEQUENCE", sequenceName = "SEQUENCE")
    protected Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "version")
    @Version
    private Long version;

    @Setter(AccessLevel.PACKAGE)
    @Getter(AccessLevel.NONE)
    @Column(name = "deleted")
    private boolean deleted;
    
}