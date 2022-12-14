package payment.saga.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import payment.saga.order.dto.request.OrderDtoRequest;
import payment.saga.order.model.OrderPurchase;
import payment.saga.order.service.OrderService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Manage orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create a order")
    public Mono<OrderPurchase> createOrder(@RequestBody OrderDtoRequest order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    @Operation(summary = "Obtain all orders")
    public Flux<OrderPurchase> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtain a specific order")
    public Mono<OrderPurchase> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Obtain all orders in stream protocol")
    public Flux<OrderPurchase> getAllOrdersStream() {
        return orderService.reactiveGetAll();
    }

}
