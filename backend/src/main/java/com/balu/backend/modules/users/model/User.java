package com.balu.backend.modules.users.model;

import com.balu.backend.modules.roles.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text", nullable = false)
    private String email;
    @Column(columnDefinition = "text", nullable = false)
    private String password;
    private Long attempts;

    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_access;
    @Column(columnDefinition = "BOOL default false")
    private boolean blocked;
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime blocked_at;
    @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
