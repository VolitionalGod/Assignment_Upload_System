package com.university.assignmentupload.service.impl;

import com.university.assignmentupload.dao.SubjectRepository;
import com.university.assignmentupload.dto.SubjectsDto;
import com.university.assignmentupload.dto.SubjectsPostDto;
import com.university.assignmentupload.exception.ResourceNotFoundException;
import com.university.assignmentupload.mapper.SubjectsMapper;
import com.university.assignmentupload.model.Subjects;
import com.university.assignmentupload.service.SubjectsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectsServiceImpl implements SubjectsService {

    private final SubjectRepository subjectRepository;

    private final SubjectsMapper subjectsMapper;

    public SubjectsServiceImpl(SubjectRepository subjectRepository, SubjectsMapper subjectsMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectsMapper = subjectsMapper;
    }

    @Override
    public void updateSubjects(SubjectsDto subjectsDto) {
        this.subjectRepository.save(subjectsMapper.subjectsDtoToSubjects(subjectsDto));
    }

    @Override
    public List<SubjectsDto> getSubjects() {
        List<Subjects> list = subjectRepository.findAll();
        return this.subjectsMapper.programmesToProgrammesPost(list);
    }

    @Override
    public SubjectsDto saveSubjects(SubjectsPostDto subjectsPostDto) {
        Subjects subjects = subjectsMapper.subjectsPostDtoToSubjects(subjectsPostDto);
        subjects = this.subjectRepository.save(subjects);
        return subjectsMapper.programmesToProgrammesPost(subjects);
    }

    @Override
    public SubjectsDto getSubjectById(Long id) {
        Optional<Subjects> optional = this.subjectRepository.findById(id);
        return optional.map(subjectsMapper::programmesToProgrammesPost)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<SubjectsDto> getActiveSubjects() {
        List<Subjects> list = subjectRepository.findAllByActive(Boolean.TRUE);
        return this.subjectsMapper.programmesToProgrammesPost(list);
    }
}
