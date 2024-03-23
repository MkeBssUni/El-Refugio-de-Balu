package com.balu.backend.modules.logs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LogTypes type;
    @Column(nullable = false)
    private String tableAffected;
    @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public void saveLog(String message, LogTypes logTypes, String tableAffected){
        this.message = message;
        this.type = logTypes;
        this.tableAffected = tableAffected;
    }
}
