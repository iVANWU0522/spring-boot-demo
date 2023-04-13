package com.ivan.orm.jpa.entity;

import com.ivan.orm.jpa.entity.base.AbstractAuditModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orm_user")
@ToString(callSuper = true)
public class User extends AbstractAuditModel {
    private String name;
    private String password;
    private String salt;
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Integer status;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * Associates with the department table.
     * 1. The relationship maintenance end is responsible for binding and unbinding the many-to-many relationship.
     * 2. The name attribute of the @JoinTable annotation specifies the name of the associated table, joinColumns specifies the name of the foreign key associated with the relationship maintenance end (User).
     * 3. inverseJoinColumns specifies the name of the foreign key to be associated with the relationship being maintained (Department).
     * 4. In fact, @JoinTable annotation is not necessary. The default name of the generated association table is the name of the main table followed by an underscore and the name of the subordinate table, that is, user_department.
     * The name of the foreign key associated with the main table is: the name of the main table followed by an underscore and the name of the primary key column in the main table, that is, user_id, which is specified using referencedColumnName.
     * The name of the foreign key associated with the subordinate table is: the name of the attribute used to associate in the main table followed by an underscore and the name of the primary key column in the subordinate table, that is, department_id.
     * The main table is the table corresponding to the relationship maintenance end, and the subordinate table is the table corresponding to the relationship being maintained.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "orm_user_dept",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dept_id", referencedColumnName = "id")
    )
    private Collection<Department> departmentList;
}
