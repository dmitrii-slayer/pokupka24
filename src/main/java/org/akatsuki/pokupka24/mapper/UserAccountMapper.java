package org.akatsuki.pokupka24.mapper;

import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.dto.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserAccountMapper {

    @Mapping(source = "user.userId", target = "userId")
    UserAccountDTO toDTO(UserAccount userAccount);

    @Mapping(source = "userId", target = "user.userId")
    UserAccount toEntity(UserAccountDTO accountDTO);

    List<UserAccountDTO> toDTOList(List<UserAccount> accounts);

}
