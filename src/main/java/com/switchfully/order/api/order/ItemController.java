package com.switchfully.order.api.order;

import com.switchfully.order.service.order.ItemService;
import com.switchfully.order.service.support.dto.order.ItemDTO;
import com.switchfully.order.service.user.SecurityService;
import com.switchfully.order.service.support.dto.order.CreateItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.switchfully.order.domain.models.user.Feature.CAN_CREATE_ITEM;
import static com.switchfully.order.domain.models.user.Feature.CAN_RETRIEVE_ALL_ITEMS;

@RestController
@RequestMapping(value = "items")
public class ItemController {
    private final ItemService itemService;
    private final SecurityService securityService;

    private final Logger myLogger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", value = "")
    public void createItem(@RequestBody CreateItemDTO createItemDTO, @RequestHeader(required = false) String authorization){
        myLogger.info("Adding a New Item to the Database.");
        securityService.validateUser(authorization, CAN_CREATE_ITEM);
        itemService.createItem(createItemDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "")
    public List<ItemDTO> getAllItems(@RequestHeader(required = false) String authorization) {
        myLogger.info("Retrieving All the Items from the Database.");
        securityService.validateUser(authorization, CAN_RETRIEVE_ALL_ITEMS);
        return itemService.getAllItems();
    }
}
