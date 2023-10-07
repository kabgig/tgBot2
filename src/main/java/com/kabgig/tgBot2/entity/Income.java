package com.kabgig.tgBot2.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "INCOMES")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "INCOME")
    private BigDecimal income;

    @Column(name = "CREATIONDATE")
    private LocalDateTime creationDate;
}
