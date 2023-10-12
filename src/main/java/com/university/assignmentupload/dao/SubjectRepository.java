package com.university.assignmentupload.dao;

import com.university.assignmentupload.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects, Long> {

    List<Subjects> findAll();

    List<Subjects> findAllByActive(Boolean active);
}
