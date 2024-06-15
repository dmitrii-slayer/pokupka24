package org.akatsuki.pokupka24.mapper;

import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    List<UserDTO> toDTOList(List<User> users);
}
