package com.practice.shoppingmall.domain.item.presentation.dto.response;

import com.practice.shoppingmall.domain.item.domain.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindItemResponse {

    private Long itemId;

    private String itemName;

    private Integer price;

    private Integer stock;

    private Boolean isSoldOut;
    public static FindItemResponse of (Item item) {

        return FindItemResponse
                .builder()
                .itemId(item.getId())
                .itemName(item.getName())
                .price(item.getPrice())
                .stock(item.getStock())
                .isSoldOut(item.getStock() == 0)
                .build();
    }
}