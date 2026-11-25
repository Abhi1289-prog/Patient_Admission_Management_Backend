package com.dscribe.admission.patient;

import com.dscribe.admission.lookup.AdmissionStatus;
import com.dscribe.admission.lookup.Gender;

import java.time.LocalDate;

public record PatientResponseDto(
        Long id,
        String name,
        Integer age,
        Gender gender,
        Long wardId,
        String wardName,
        Long departmentId,
        String departmentName,
        Long doctorId,
        String doctorName,
        LocalDate admissionDate,
        AdmissionStatus status
) {
    public static PatientResponseDto from(Patient p) {
        return new PatientResponseDto(
                p.getId(),
                p.getName(),
                p.getAge(),
                p.getGender(),
                p.getWard().getId(),
                p.getWard().getName(),
                p.getDepartment().getId(),
                p.getDepartment().getName(),
                p.getDoctor().getId(),
                p.getDoctor().getName(),
                p.getAdmissionDate(),
                p.getStatus()
        );
    }
}
