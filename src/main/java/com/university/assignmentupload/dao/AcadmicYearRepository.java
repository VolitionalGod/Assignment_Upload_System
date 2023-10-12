package com.university.assignmentupload.dao;

import com.university.assignmentupload.model.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcadmicYearRepository extends JpaRepository<AcademicYear, Long> {
    List<AcademicYear> findAllByActive(boolean active);
}
