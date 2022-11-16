package domain.team;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberProductId implements Serializable {

    private Long member;

    private Long product;
}
