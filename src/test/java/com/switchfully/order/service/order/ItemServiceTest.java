package com.switchfully.order.service.order;

import com.switchfully.order.domain.repositories.order.ItemRepository;
import com.switchfully.order.service.support.mapper.order.CreateItemMapper;
import com.switchfully.order.service.support.mapper.order.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

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

}