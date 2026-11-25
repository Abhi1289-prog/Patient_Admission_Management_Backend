package com.dscribe.admission.lookup;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lookups")
@RequiredArgsConstructor
public class LookupController {

    private final WardRepository wardRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    public record LookupResponse(
            List<Ward> wards,
            List<Department> departments,
            List<Doctor> doctors,
            Gender[] genders,
            AdmissionStatus[] statuses
    ) {}

    @GetMapping
    public LookupResponse getAllLookups() {
        Sort byName = Sort.by("name").ascending();
        return new LookupResponse(
                wardRepository.findAll(byName),
                departmentRepository.findAll(byName),
                doctorRepository.findAll(byName),
                Gender.values(),
                AdmissionStatus.values()
        );
    }
}
