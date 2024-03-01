package com.effectiveMobile.testTask.mapper;

import com.effectiveMobile.testTask.dto.EmailDto;
import com.effectiveMobile.testTask.entity.EmailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailEntity emailDtoToEmailEntity(EmailDto emailDto);
}
