package com.effectiveMobile.testTask.mapper;

import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.request.UserCreationRequest;
import com.effectiveMobile.testTask.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
    UserEntity userCreationRequestToUserEntity(UserCreationRequest userCreationRequest);

    UserResponse userEntityToUserDto(UserEntity userEntity);
}
