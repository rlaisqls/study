package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.item.AddItemStockRequest;
import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.ResponseBody;
import com.practice.shoppingmall.dto.response.item.FindItemResponse;
import com.practice.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    public ResponseBody createItem(@Valid @RequestBody CreateItemRequest request){
        return ResponseBody.of(itemService.createItem(request), HttpStatus.CREATED.value());
    }

    @PatchMapping("/item")
    public void patchItem(@Valid @RequestBody ModifyItemInfoRequest request){
        itemService.patchItem(request);
    }

    @PostMapping("/item/add")
    public void addItemStock(@Valid @RequestBody AddItemStockRequest request){
        itemService.addItemStock(request);
    }

    @DeleteMapping("/item")
    public void deleteItemStock(@Valid @RequestBody DeleteItemRequest request){
        itemService.deleteItemStock(request);
    }

    @GetMapping("/item")
    public List<FindItemResponse> findItemList(){
        return itemService.findItemList();
    }

    @GetMapping("/item/{itemUuid}")
    public FindItemResponse findItem(@PathVariable String itemUuid){
        return itemService.findItem(itemUuid);
    }
}