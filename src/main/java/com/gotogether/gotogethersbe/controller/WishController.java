package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.dto.WishDto;
import com.gotogether.gotogethersbe.service.WishService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WishController {

    private final WishService wishService;

    // 찜하기
    @PostMapping("/wishes")
    public DefaultRes doWish(@RequestBody WishDto.WishRequest request) {

        return wishService.doWish(request, SecurityUtil.getCurrentMemberId());
    }

    // 찜 목록 조회
    @GetMapping("/wishes")
    public DefaultRes getWishList() {

        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_WISH_LIST, wishService.getWishList(SecurityUtil.getCurrentMemberId()));
    }

    // 찜 선택 삭제
    @DeleteMapping("/wishes")
    public DefaultRes deleteWishes(@RequestBody WishDto.WishDeleteRequest request) {

        wishService.deleteWishes(request.getWish_id(), SecurityUtil.getCurrentMemberId());

        return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_WISHES);
    }
}