package com.effectiveMobile.testTask.mapper;

import com.effectiveMobile.testTask.dto.PhoneDto;
import com.effectiveMobile.testTask.entity.PhoneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    PhoneEntity phoneDtoToPhoneEntity(PhoneDto phoneDto);
}
