package com.dscribe.admission.patient;

import org.springframework.data.jpa.domain.Specification;

public class PatientSpecifications {

    public static Specification<Patient> withFilters(String name, Long wardId, Long departmentId, Long doctorId) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (name != null && !name.isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("name")), "%" + name.trim().toLowerCase() + "%"));
            }
            if (wardId != null) {
                predicates = cb.and(predicates, cb.equal(root.get("ward").get("id"), wardId));
            }
            if (departmentId != null) {
                predicates = cb.and(predicates, cb.equal(root.get("department").get("id"), departmentId));
            }
            if (doctorId != null) {
                predicates = cb.and(predicates, cb.equal(root.get("doctor").get("id"), doctorId));
            }
            return predicates;
        };
    }
}