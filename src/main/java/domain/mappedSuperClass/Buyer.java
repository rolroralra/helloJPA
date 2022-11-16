package domain.mappedSuperClass;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BUYER")
@Setter
@Getter
public class Buyer extends BaseEntity {
    private String address;
    private String phoneNumber;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Buyer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
