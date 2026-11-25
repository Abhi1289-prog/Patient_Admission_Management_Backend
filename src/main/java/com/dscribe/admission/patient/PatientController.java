package com.dscribe.admission.patient;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientResponseDto> getPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long wardId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long doctorId
    ) {
        return patientService.getPatients(name, wardId, departmentId, doctorId);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(dto));
    }

    @PutMapping("/{id}")
    public PatientResponseDto updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRequestDto dto) {
        return patientService.updatePatient(id, dto);
    }

    @PatchMapping("/{id}/discharge")
    public PatientResponseDto dischargePatient(@PathVariable Long id) {
        return patientService.dischargePatient(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}