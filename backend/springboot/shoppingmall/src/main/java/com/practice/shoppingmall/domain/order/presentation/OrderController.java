package com.practice.shoppingmall.domain.order.presentation;

import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderRequest;
import com.practice.shoppingmall.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public void doOrder(@Valid @RequestBody OrderRequest requests){
        orderService.doOrder(requests);
    }

    @PatchMapping("/order/{orderId}")
    public void cancelOrder(@PathVariable String orderId){
        orderService.cancelOrder(orderId);
    }

    @GetMapping("/order/{orderId}")
    public ResponseBody findOneOrder(@PathVariable UUID orderId){
        return ResponseBody.of(orderService.findOneOrder(orderId), HttpStatus.OK.value());
    }

    @GetMapping("/order")
    public ResponseBody findMyOrder(int page, int size){
        return ResponseBody.of(orderService.findMyOrder(page, size), HttpStatus.OK.value());
    }
}