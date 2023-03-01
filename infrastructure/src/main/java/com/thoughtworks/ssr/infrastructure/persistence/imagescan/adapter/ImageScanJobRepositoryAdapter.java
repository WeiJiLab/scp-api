package com.thoughtworks.ssr.infrastructure.persistence.imagescan.adapter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanJobRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter.ImageScanJobEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanCommandEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanJobJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageScanJobRepositoryAdapter implements ImageScanJobRepository {

    private final ImageScanJobJpaRepository jpaRepository;

    private final ImageScanJobEntityConverter converter;

    @Override
    public ImageScanCommand save(ImageScanCommand imageScanCommand) {
        var imageScanCommandEntity = jpaRepository.save(converter.from(imageScanCommand));
        return converter.toDomain(imageScanCommandEntity);
    }

    @Override
    public List<ImageScanCommand> findAll() {
        List<ImageScanCommandEntity> commandEntities = jpaRepository.findAll();
        List<ImageScanCommand> allCommands = new ArrayList<>();
        for (ImageScanCommandEntity command : commandEntities) {
            ImageScanCommand imageScanCommand = converter.toDomain(command);
            allCommands.add(imageScanCommand);
        }
        return allCommands;
    }

}
