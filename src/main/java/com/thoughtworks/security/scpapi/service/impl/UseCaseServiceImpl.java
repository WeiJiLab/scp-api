package com.thoughtworks.security.scpapi.service.impl;

import com.amazonaws.AmazonServiceException;
import com.thoughtworks.security.scpapi.api.s3.OperateObject;
import com.thoughtworks.security.scpapi.dto.ComplianceUseCaseInputDto;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.exception.UploadFileException;
import com.thoughtworks.security.scpapi.exception.UseCaseNotFoundException;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.UseCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.thoughtworks.security.scpapi.util.ConstantsValue.S3_BUCKET_NAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class UseCaseServiceImpl implements UseCaseService {
    private final UseCaseRepository useCaseRepository;

    @Override
    public UseCaseEntity addUseCase(MultipartFile file, String description, String name, Integer toolId) {

        String filePath = uploadFile(file);

        UseCaseEntity useCaseEntity = UseCaseEntity.builder()
                .scriptPath(filePath)
                .securityToolId(toolId)
                .name(name)
                .description(description)
                .build();

        return useCaseRepository.saveAndFlush(useCaseEntity);
    }

    private String uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new UploadFileException("File is empty");
        }

        try {
            // TODO : 修改文件名
            Date date = new Date();
            String currDate = new Timestamp(date.getTime()).toString().replaceAll("[[\\s-:punct:]]", "");
            String keyName = Objects.requireNonNull(file.getOriginalFilename()).substring(0,
                    file.getOriginalFilename().lastIndexOf(".")) + currDate + ".zip";
            String filePath = "/tmp/" + keyName;
            File dest = new File(filePath);
            file.transferTo(dest);
            OperateObject.putObject(filePath, S3_BUCKET_NAME);

            dest.deleteOnExit();
            return filePath;

        } catch (IOException e) {
            log.info("upload file error");
            throw new UploadFileException("upload file error");
        } catch (AmazonServiceException amazonServiceException) {
            throw new UploadFileException("s3 upload file error");
        }

    }

    @Override
    @Transactional
    public void deleteUseCase(List<Long> useCaseIds) {
        List<UseCaseEntity> useCases = useCaseRepository.findAllByIdIn(useCaseIds);

        List<String> keyNames = useCases.stream()
                .map(UseCaseEntity::getScriptPath)
                .collect(Collectors.toList());

        OperateObject.deleteObjects(S3_BUCKET_NAME, keyNames);
        useCaseRepository.deleteAllByIdIn(useCaseIds);
    }

    @Override
    public void modifyUseCase(ComplianceUseCaseInputDto useCaseInputDto) {
        //TODO修改功能暂时不做，因为修改需要考虑修改的到底是哪些字段，业务复杂，暂时先做核心业务
    }


    @Override
    public void deleteById(Long id) {
        try {
            useCaseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new UseCaseNotFoundException();
        }
    }

    @Override
    public UseCaseEntity findById(Long id) {
        return useCaseRepository.findById(id)
                .orElseThrow(UseCaseNotFoundException::new);
    }

    @Override
    public UseCaseEntity update(Long id, MultipartFile file, String description, String name, Integer toolId) {

        UseCaseEntity useCaseEntity = useCaseRepository.findById(id)
                .orElseThrow(UseCaseNotFoundException::new);

        String filePath = uploadFile(file);

        useCaseEntity.setScriptPath(filePath);
        useCaseEntity.setDescription(description);
        useCaseEntity.setName(name);
        useCaseEntity.setSecurityToolId(toolId);

        return useCaseRepository.save(useCaseEntity);
    }

    @Override
    public List<UseCaseEntity> findAll() {
        return useCaseRepository.findAll();
    }

    @Override
    public List<UseCaseEntity> findUseCaseByToolId(Integer id) {
        return useCaseRepository.findAllBySecurityToolId(id);
    }
}
