package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.domain.Wish;
import com.gotogether.gotogethersbe.dto.WishDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import com.gotogether.gotogethersbe.repository.WishRepository;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WishService {

    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 찜 하기
    @Transactional
    public DefaultRes doWish(WishDto.WishRequest request, Long id) {

        if(wishRepository.findByProduct_idAndMember_id(request.getProduct_id(), id).isEmpty()) {

            Wish wish = wishBuilder(request, id);
            wishRepository.save(wish);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.WISH_SUCCESS);
        }
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.ALREADY_EXIST_WISH);
    }

    private Wish wishBuilder(WishDto.WishRequest request, Long id) {

        return Wish.builder()
                .product(productRepository.findById(request.getProduct_id())
                        .orElseThrow(() -> new CustomException(ResponseMessage.NOT_FOUND_PRODUCT, StatusCode.NOT_FOUND)))
                .member(memberRepository.findById(id).get())
                .build();
    }

    // 찜 목록 조회
    @Transactional(readOnly = true)
    public List<WishDto.WishListResponse> getWishList(Long id) {

        return maptoDto(wishRepository.findByMember_idOrderByIdDesc(id));
    }

    private List<WishDto.WishListResponse> maptoDto(List<Wish> wishList) {

        return wishList
                .stream()
                .map(WishDto.WishListResponse::new)
                .toList();
    }

    // 찜 선택 삭제
    @Transactional
    public void deleteWishes(List<Long> wishIdList, Long id) {
        wishRepository.deleteAllByIdInQuery(wishIdList, id);
    }
}
