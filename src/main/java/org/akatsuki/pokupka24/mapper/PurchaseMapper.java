package org.akatsuki.pokupka24.mapper;

import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseDTO toDTO(Purchase purchase);

    Purchase toEntity(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> toDTOList(List<Purchase> purchases);
}
