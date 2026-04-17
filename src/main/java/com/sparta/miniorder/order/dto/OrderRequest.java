package com.sparta.miniorder.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequest {
    @NotNull(message = "주문할 상품 ID는 필수입니다.")
    private Long productId;
}
