package com.switchfully.order.api;

import com.switchfully.order.domain.models.users.Feature;
import com.switchfully.order.service.ItemService;
import com.switchfully.order.service.SecurityService;
import com.switchfully.order.service.dto.CreateItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.switchfully.order.domain.models.users.Feature.CAN_CREATE_ITEM;

@RestController
@RequestMapping(value = "items")
public class ItemController {
    private final ItemService itemService;
    private final SecurityService securityService;

    private final Logger myLogger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", value = "")
    public void createItem(@RequestBody CreateItemDTO createItemDTO, @RequestHeader(required = false) String authorization){
        myLogger.info("Adding a New Item to the Database.");
        securityService.validateAuthorization(authorization, CAN_CREATE_ITEM);
        itemService.createItem(createItemDTO);
    }
}
