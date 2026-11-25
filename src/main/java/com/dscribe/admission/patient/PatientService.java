package com.dscribe.admission.patient;

import com.dscribe.admission.exception.ResourceNotFoundException;
import com.dscribe.admission.lookup.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final WardRepository wardRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<PatientResponseDto> getPatients(String name, Long wardId, Long departmentId, Long doctorId) {
        var spec = PatientSpecifications.withFilters(name, wardId, departmentId, doctorId);
        return patientRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(PatientResponseDto::from)
                .toList();
    }

    @Transactional
    public PatientResponseDto createPatient(PatientRequestDto dto) {
        Patient patient = new Patient();
        mapRequestToEntity(dto, patient);
        patient.setStatus(dto.status() != null ? dto.status() : AdmissionStatus.ADMITTED);
        return PatientResponseDto.from(patientRepository.save(patient));
    }

    @Transactional
    public PatientResponseDto updatePatient(Long id, PatientRequestDto dto) {
        Patient patient = patientRepository.findWithDetailsById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        mapRequestToEntity(dto, patient);
        if (dto.status() != null) {
            patient.setStatus(dto.status());
        }
        return PatientResponseDto.from(patientRepository.save(patient));
    }

    @Transactional
    public PatientResponseDto dischargePatient(Long id) {
        Patient patient = patientRepository.findWithDetailsById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patient.setStatus(AdmissionStatus.DISCHARGED);
        return PatientResponseDto.from(patientRepository.save(patient));
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    private void mapRequestToEntity(PatientRequestDto dto, Patient patient) {
        Ward ward = wardRepository.findById(dto.wardId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ward selected"));
        Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid department selected"));
        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid doctor selected"));

        patient.setName(dto.name().trim());
        patient.setAge(dto.age());
        patient.setGender(dto.gender());
        patient.setWard(ward);
        patient.setDepartment(department);
        patient.setDoctor(doctor);
        patient.setAdmissionDate(dto.admissionDate());
    }
}
