package com.switchfully.order.service.support.mapper.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.service.support.dto.order.ItemDTO;
import com.switchfully.order.service.support.dto.order.ItemGroupDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemGroupMapper {

//    public ItemGroup toItemGroup(ItemGroupDTO itemGroupDTO) {
//        return new ItemGroup(itemGroupDTO.getItemId(), itemGroupDTO.getItemName(), itemGroupDTO.getAmount(),
//                );
//    }
    public ItemGroupDTO toItemGroupDTO(ItemGroup itemGroup) {
        return new ItemGroupDTO(itemGroup.getItemId(), itemGroup.getItemName(), itemGroup.getAmount(),
                itemGroup.getPrice(), itemGroup.getPrice(), itemGroup.getShippingDate());
    }

    public List<ItemGroupDTO> toItemGroupDTOList(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(this::toItemGroupDTO)
                .toList();
    }

//    public List<ItemGroup> toItemGroupList(List<ItemGroupDTO> itemGroupListDTO) {
//        return itemGroupListDTO.stream()
//                .map(this::toItemGroup)
//                .toList();
//    }
}
