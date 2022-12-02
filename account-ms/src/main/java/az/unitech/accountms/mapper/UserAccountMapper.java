package az.unitech.accountms.mapper;

import az.unitech.accountms.dtos.dto.UserAccountDto;
import az.unitech.accountms.model.UserAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccountMapper {

    UserAccountMapper INSTANCE= Mappers.getMapper(UserAccountMapper.class);

    UserAccountEntity toEntity(UserAccountDto dto);

    UserAccountDto toDto(UserAccountEntity entity);
}
