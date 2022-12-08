package org.example.mapping.pkfk.idclass;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildId implements Serializable {

    private Long id;            // Child.id 매핑

    private Long parent;        // Child.parent 매핑
}
