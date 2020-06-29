package com.template.sat.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "email_verification_token")
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_verification_token_token_id_seq")
    @SequenceGenerator(name = "email_verification_token_token_id_seq", sequenceName = "email_verification_token_token_id_seq", allocationSize = 1)
    @Column(name = "token_id")
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

}
