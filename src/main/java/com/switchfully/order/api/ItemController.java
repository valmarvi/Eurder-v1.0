package com.switchfully.order.api;

import com.switchfully.order.service.ItemService;
import com.switchfully.order.service.dto.CreateItemDTO;
import com.switchfully.order.service.wrapper.CustomerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "items")
public class ItemController {
    private final ItemService itemService;

    private final Logger myLogger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", value = "")
    public void createItem(@RequestBody CreateItemDTO createItemDTO){
        myLogger.info("Adding a New Item to the Database.");
        itemService.createItem(createItemDTO);
    }
}
