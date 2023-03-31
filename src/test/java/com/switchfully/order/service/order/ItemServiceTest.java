package com.switchfully.order.service.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.domain.repositories.order.ItemRepository;
import com.switchfully.order.service.support.dto.order.CreateItemDTO;
import com.switchfully.order.service.support.dto.order.ItemDTO;
import com.switchfully.order.service.support.mapper.order.CreateItemMapper;
import com.switchfully.order.service.support.mapper.order.ItemMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    ItemRepository itemRepositoryMock;
    CreateItemMapper createItemMapperMock;
    ItemMapper itemMapperMock;
    @InjectMocks
    ItemService itemService;

    @BeforeEach
    void setupUserService() {
        itemRepositoryMock = Mockito.mock(ItemRepository.class);
        createItemMapperMock = Mockito.mock(CreateItemMapper.class);
        itemMapperMock = Mockito.mock(ItemMapper.class);
        itemService = new ItemService(itemRepositoryMock, createItemMapperMock, itemMapperMock);
    }

    CreateItemDTO createItemDTO = new CreateItemDTO("Test Item", "Item to Test",
            50,10);

    ItemDTO itemDTO = new ItemDTO(UUID.randomUUID().toString(), "Test Item",
            "Item to Test", 50,10);

    @Test
    void createItem_Mock() {
        Item item = createItemMapperMock.toItem(createItemDTO);
        itemService.createItem(createItemDTO);
        Mockito.verify(itemRepositoryMock).createItem(item);
    }

    @Test
    void getAllItems_Mock() {
        itemService.getAllItems();
        Mockito.verify(itemRepositoryMock).getAllItems();
    }

    @Test
    void getAllItems_Stub() {
        Mockito.when(itemMapperMock.toItemDTOList(itemRepositoryMock.getAllItems()))
                .thenReturn(List.of(itemDTO));

        List<ItemDTO> itemDTOList = itemService.getAllItems();

        Assertions.assertThat(itemDTOList).contains(itemDTO);
    }

    @Test
    void validateIfCreateItemDTOIsNull() {
        CreateItemDTO nullCreateItemDTO = null;

        Assertions.assertThatThrownBy(()-> itemService.createItem(nullCreateItemDTO))
                .hasMessage("Create Item data must be filled");
    }

    @Test
    void validateName() {
        CreateItemDTO nullNameCreateItemDTO = new CreateItemDTO(null, "Item to Test",
                50,10);
        CreateItemDTO blankNameCreateItemDTO = new CreateItemDTO("", "Item to Test",
                50,10);

        Assertions.assertThatThrownBy(()-> itemService.createItem(nullNameCreateItemDTO))
                .hasMessage("Name must be filled");
        Assertions.assertThatThrownBy(()-> itemService.createItem(blankNameCreateItemDTO))
                .hasMessage("Name must be filled");
    }

    @Test
    void validateDescription() {
        CreateItemDTO nullDescriptionCreateItemDTO = new CreateItemDTO("Test Item", null,
                50,10);
        CreateItemDTO blankDescriptionCreateItemDTO = new CreateItemDTO("Test Item", "",
                50,10);

        Assertions.assertThatThrownBy(()-> itemService.createItem(nullDescriptionCreateItemDTO))
                .hasMessage("Description must be filled");
        Assertions.assertThatThrownBy(()-> itemService.createItem(blankDescriptionCreateItemDTO))
                .hasMessage("Description must be filled");
    }

    @Test
    void validatePrice() {
        CreateItemDTO negativePriceCreateItemDTO = new CreateItemDTO("Test Item", "Item to Test",
                -50,10);

        Assertions.assertThatThrownBy(()-> itemService.createItem(negativePriceCreateItemDTO))
                .hasMessage("The price must be greater than 0");
    }

    @Test
    void validateStockAmount() {
        CreateItemDTO negativeStockAmountItemDTO = new CreateItemDTO("Test Item", "Item to Test",
                50,-10);

        Assertions.assertThatThrownBy(()-> itemService.createItem(negativeStockAmountItemDTO))
                .hasMessage("The Stock Amount must be greater than 0");
    }
}