package com.dscribe.admission.patient;

import com.dscribe.admission.lookup.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patient", indexes = {
        @Index(name = "idx_patient_name", columnList = "name"),
        @Index(name = "idx_patient_ward", columnList = "ward_id"),
        @Index(name = "idx_patient_department", columnList = "department_id"),
        @Index(name = "idx_patient_doctor", columnList = "doctor_id")
})
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdmissionStatus status = AdmissionStatus.ADMITTED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", nullable = false)
    private Ward ward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}