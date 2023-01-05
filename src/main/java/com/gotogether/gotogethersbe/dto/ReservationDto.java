package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Reservation;
import com.gotogether.gotogethersbe.domain.enums.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

public class ReservationDto {

    // 예약하기
    @Data
    public static class ReservationRequest {

        private Long product_id;
        private ReservationDtoForReservationRequest reservationDto;
        private List<ReservationPersonDtoForReservationRequest> reservationPersonListDto;
    }

    @Data
    public static class ReservationDtoForReservationRequest {

        private int totalReservationPeople;
        private long totalBasicPrice;

        private String firstSelectOption;
        private int totalFirstSelectOptionCount;
        private long totalFirstSelectOptionPrice;

        private String secondSelectOption;
        private int totalSecondSelectOptionCount;
        private long totalSecondSelectOptionPrice;

        private String thirdSelectOption;
        private int totalThirdSelectOptionCount;
        private long totalThirdSelectOptionPrice;

        private long totalPrice;

        private String duration;
    }

    @Data
    public static class ReservationPersonDtoForReservationRequest {

        private String name;
        private String phoneNumber;
        private Boolean role;
    }
    // 예약 상품 목록 조회
    @Data
    public static class ReservationListResponse {

        private LocalDate reservationDate;
        private String reservationDayOfWeek;
        private Long reservation_id;
        private String thumbnail;

        @Enumerated(EnumType.STRING)
        private Status status;

        private Long product_id;
        private String productName;
        private String airport;
        private String duration;

        public ReservationListResponse(Reservation reservation) {

            reservationDate = reservation.getReservationDate();
            reservationDayOfWeek = reservation.getReservationDayOfWeek();
            reservation_id = reservation.getId();
            thumbnail = reservation.getProduct().getThumbnail();
            status = reservation.getStatus();
            product_id = reservation.getProduct().getId();
            productName = reservation.getProduct().getProductName();
            duration = reservation.getDuration();
            airport = reservation.getProduct().getAirport();
        }
    }

    // 예약 상품 상세 조회
    @Data
    public static class ReservationDetailResponse {

        private LocalDate reservationDate;
        private String reservationDayOfWeek;
        private Long reservation_id;
        private String thumbnail;

        @Enumerated(EnumType.STRING)
        private Status status;

        private String productName;
        private String airport;
        private String duration;
        private int totalReservationPeople;
        private long totalPrice;

        private String firstSelectOption;
        private int totalFirstSelectOptionCount;
        private long totalFirstSelectOptionPrice;

        private String secondSelectOption;
        private int totalSecondSelectOptionCount;
        private long totalSecondSelectOptionPrice;

        private String thirdSelectOption;
        private int totalThirdSelectOptionCount;
        private long totalThirdSelectOptionPrice;


        public ReservationDetailResponse(Reservation reservation){
            reservationDate = reservation.getReservationDate();
            reservationDayOfWeek = reservation.getReservationDayOfWeek();
            reservation_id = reservation.getId();
            thumbnail = reservation.getProduct().getThumbnail();
            status = reservation.getStatus();
            productName = reservation.getProduct().getProductName();
            airport = reservation.getProduct().getAirport();
            duration = reservation.getDuration();
            totalReservationPeople = reservation.getTotalReservationPeople();
            totalPrice = reservation.getTotalPrice();

            firstSelectOption = reservation.getFirstSelectOption();
            totalFirstSelectOptionCount = reservation.getTotalFirstSelectOptionCount();
            totalFirstSelectOptionPrice = reservation.getTotalFirstSelectOptionPrice();

            secondSelectOption = reservation.getSecondSelectOption();
            totalSecondSelectOptionCount = reservation.getTotalSecondSelectOptionCount();
            totalSecondSelectOptionPrice = reservation.getTotalSecondSelectOptionPrice();

            thirdSelectOption = reservation.getThirdSelectOption();
            totalThirdSelectOptionCount = reservation.getTotalThirdSelectOptionCount();
            totalThirdSelectOptionPrice = reservation.getTotalThirdSelectOptionPrice();
        }
    }

    // 예약 상태(예약대기, 예약완료, 예약취소) 수정
    @Data
    public static class UpdateReservationStatusRequest {

        private Long reservation_id;
        private Status status;
    }

    // 에약 상품 삭제
    @Data
    public static class DeleteReservationRequest {

        private Long reservation_id;
    }
}
