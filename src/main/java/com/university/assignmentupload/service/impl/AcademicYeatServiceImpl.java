package com.university.assignmentupload.service.impl;

import com.university.assignmentupload.dao.AcadmicYearRepository;
import com.university.assignmentupload.dto.AcademicYearDto;
import com.university.assignmentupload.dto.AcademicYearPostDto;
import com.university.assignmentupload.exception.ResourceNotFoundException;
import com.university.assignmentupload.mapper.AcademicYearMapper;
import com.university.assignmentupload.model.AcademicYear;
import com.university.assignmentupload.service.AcademicYearService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicYeatServiceImpl implements AcademicYearService {

    private final AcademicYearMapper academicYearMapper;

    private final AcadmicYearRepository acadmicYearRepository;

    public AcademicYeatServiceImpl(AcademicYearMapper academicYearMapper, AcadmicYearRepository acadmicYearRepository) {
        this.academicYearMapper = academicYearMapper;
        this.acadmicYearRepository = acadmicYearRepository;
    }

    @Override
    public List<AcademicYearDto> getAcademicYears() {
        List<AcademicYear> academicYearList = acadmicYearRepository.findAll();
        return academicYearMapper.academicYearsToAcademicYearDtos(academicYearList);
    }

    @Override
    public List<AcademicYearDto> getActiveAcademicYears() {
        List<AcademicYear> academicYearList = acadmicYearRepository.findAllByActive(Boolean.TRUE);
        return academicYearMapper.academicYearsToAcademicYearDtos(academicYearList);
    }

    @Override
    public AcademicYearDto getAcademicYearById(Long id) {
        Optional<AcademicYear> optional = acadmicYearRepository.findById(id);
        return optional.map(academicYearMapper::academicYearToAcademicYearDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public AcademicYearDto saveAcademicYear(AcademicYearPostDto academicYearPostDto) {
        AcademicYear academicYear = academicYearMapper.academicYearPostDtoToAcademicYear(academicYearPostDto);
        academicYear = acadmicYearRepository.save(academicYear);
        return academicYearMapper.academicYearToAcademicYearDto(academicYear);
    }

    @Override
    public void updateAcademicYear(AcademicYearDto academicYearDto) {
        this.acadmicYearRepository.save(academicYearMapper.academicYearDtoToAcademicYear(academicYearDto));
    }
}
