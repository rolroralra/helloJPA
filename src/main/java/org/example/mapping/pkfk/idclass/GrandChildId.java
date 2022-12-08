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
public class GrandChildId implements Serializable {
    private Long id;        // GrandChild.id 매핑
    private ChildId child;     // GrandChild.child 매핑
}
