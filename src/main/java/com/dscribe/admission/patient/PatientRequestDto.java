package com.dscribe.admission.patient;

import com.dscribe.admission.lookup.AdmissionStatus;
import com.dscribe.admission.lookup.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PatientRequestDto(
        @NotBlank(message = "Name is required")
        @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain letters only")
        String name,

        @NotNull(message = "Age is required")
        @Min(value = 0, message = "Age must be between 0 and 120")
        @Max(value = 120, message = "Age must be between 0 and 120")
        Integer age,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Ward is required")
        Long wardId,

        @NotNull(message = "Department is required")
        Long departmentId,

        @NotNull(message = "Doctor is required")
        Long doctorId,

        @NotNull(message = "Admission date is required")
        @PastOrPresent(message = "Admission date cannot be a future date")
        LocalDate admissionDate,

        AdmissionStatus status
){}
