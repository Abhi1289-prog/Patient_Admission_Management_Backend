package com.dscribe.admission.patient;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    @Override
    @EntityGraph(attributePaths = {"ward", "department", "doctor"})
    List<Patient> findAll(Specification<Patient> spec, Sort sort);

    @EntityGraph(attributePaths = {"ward", "department", "doctor"})
    Optional<Patient> findWithDetailsById(Long id);
}