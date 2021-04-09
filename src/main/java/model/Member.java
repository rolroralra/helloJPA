package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "MEMBER", uniqueConstraints = {})
@SequenceGenerators(value = {
        @SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 50)
})
@TableGenerators(value = {
        @TableGenerator(name = "MEMBER_TABLE_GENERATOR", table = "MY_SEQUENCES", pkColumnValue = "MEMBER_SEQ", initialValue = 0, allocationSize = 50)
})
//@Table(uniqueConstraints = {@UniqueConstraint(name = "")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
//    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_TABLE_GENERATOR")
    private Long id;

    @Column(name = "name")
//    @Column(nullable = false, insertable = false, updatable = false, unique = true)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private String temp;
}
