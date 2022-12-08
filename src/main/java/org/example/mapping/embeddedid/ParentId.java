package org.example.mapping.embeddedid;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentId implements Serializable {
//    @Column(name = "ID1")
    private Long id1;
//    @Column(name = "ID2")
    private Long id2;
}
