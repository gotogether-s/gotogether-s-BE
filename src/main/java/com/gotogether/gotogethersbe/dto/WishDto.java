package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Wish;
import lombok.Data;

import java.util.List;

public class WishDto {
    // 찜 하기
    @Data
    public static class WishRequest {

        private Long product_id;
    }

    // 찜 목록 조회
    @Data
    public static class WishListResponse {

        private Long wish_id;
        private Long product_id;
        private String productName;
        private String thumbnail;
        private String airport;
        private long basicPrice;

        public WishListResponse(Wish wish) {

            wish_id = wish.getId();
            product_id = wish.getProduct().getId();
            productName = wish.getProduct().getProductName();
            thumbnail = wish.getProduct().getThumbnail();
            basicPrice = wish.getProduct().getBasicPrice();
            airport = wish.getProduct().getAirport();
        }
    }

    // 찜 선택 삭제
    @Data
    public static class WishDeleteRequest {

        private List<Long> wish_id;
    }
}
