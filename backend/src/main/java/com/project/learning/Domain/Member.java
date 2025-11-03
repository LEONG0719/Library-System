package com.project.learning.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Member")
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberId;

    @NonNull
    @Column(name="member_name",nullable=false, length=100)
    private String member_name;

    @Column(name="member_email",length=100)
    private String member_email;

    @NonNull
    @Column(name="member_phone",nullable = false, length=1000)
    private String member_phone;

    @NonNull
    @Column(name="joinDate",length=100)
    private LocalDate joinDate;


}
