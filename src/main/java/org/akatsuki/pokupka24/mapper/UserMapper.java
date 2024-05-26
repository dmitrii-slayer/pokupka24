package org.akatsuki.pokupka24.mapper;

import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
