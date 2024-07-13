package com.ayagmar.jobapplicationtracker.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenericMapper {
    private final ModelMapper modelMapper;

    public <D, E> D toDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <D, E> E toEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <S, D> D updateEntityFromDto(S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    public <D, E> List<D> toDtoList(List<E> entityList, Class<D> dtoClass) {
        return entityList.stream()
                .map(entity -> toDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public <D, E> List<E> toEntityList(List<D> dtoList, Class<E> entityClass) {
        return dtoList.stream()
                .map(dto -> toEntity(dto, entityClass))
                .collect(Collectors.toList());
    }
}
