package az.unitech.authuserms.mapper;

import az.unitech.authuserms.dtos.dto.UserDto;
import az.unitech.authuserms.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);
}
