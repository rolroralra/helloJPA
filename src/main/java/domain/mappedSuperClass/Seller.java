package domain.mappedSuperClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SELLER")
@Setter
@Getter
public class Seller extends BaseEntity {
    private String shopAddress;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Seller{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", shopAddress='").append(shopAddress).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
