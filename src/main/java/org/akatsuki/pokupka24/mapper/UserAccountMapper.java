package org.akatsuki.pokupka24.mapper;

import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.dto.UserAccountDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountDTO toDTO(UserAccount userAccount);

    UserAccount toEntity(UserAccountDTO accountDTO);

    List<UserAccountDTO> toDTOList(List<UserAccount> accounts);

}
