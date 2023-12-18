package com.danit.springrest.domain;

import com.danit.springrest.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    @JsonIgnore
    private Long roleId;

    @Column(name = "role_name", length = 30)
    private String roleName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer user;

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", sysUser=" + (user == null ? "" : user.getName()) +
                '}';
    }
}
