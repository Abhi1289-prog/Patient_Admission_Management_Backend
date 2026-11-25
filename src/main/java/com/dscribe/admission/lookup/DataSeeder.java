package com.dscribe.admission.lookup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final WardRepository wardRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public void run(String... args) {
        if (wardRepository.count() == 0) {
            wardRepository.saveAll(List.of(
                    new Ward("General Ward"),
                    new Ward("ICU"),
                    new Ward("NICU"),
                    new Ward("Private Suite"),
                    new Ward("Emergency")
            ));
        }
        if (departmentRepository.count() == 0) {
            departmentRepository.saveAll(List.of(
                    new Department("Cardiology"),
                    new Department("Neurology"),
                    new Department("Orthopedics"),
                    new Department("Pediatrics"),
                    new Department("General Medicine")
            ));
        }
        if (doctorRepository.count() == 0) {
            doctorRepository.saveAll(List.of(
                    new Doctor("Dr. Aarav Sharma"),
                    new Doctor("Dr. Priya Nair"),
                    new Doctor("Dr. Rohan Mehta"),
                    new Doctor("Dr. Sneha Rao"),
                    new Doctor("Dr. Vikram Iyer")
            ));
        }
    }
}