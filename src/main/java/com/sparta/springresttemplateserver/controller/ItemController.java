package com.sparta.springresttemplateserver.controller;

import com.sparta.springresttemplateserver.dto.ItemResponseDto;
import com.sparta.springresttemplateserver.dto.UserRequestDto;
import com.sparta.springresttemplateserver.entity.Item;
import com.sparta.springresttemplateserver.service.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // [1]서버 - 하나 받고 다시 주기
    @GetMapping("/get-call-obj") // Client측 restTemplate.getForEntity(uri, ItemDto.class)로부터 해당 경로로 쿼리문으로 변환된 상태로 요청이 들어옴
    public Item getCallObject(@RequestParam String query) {
        return itemService.getCallObject(query);
    }

    // [2]서버 - String 받고 하나씩 객체 배열만들어서 모두 응답해주기
    @GetMapping("/get-call-list")
    public ItemResponseDto getCallList() {
        return itemService.getCallList();
    }

    // [3]서버 - PathVariable
    @PostMapping("/post-call/{query}") //PathVariable 방식으로 받고 있음
    public Item postCall(@PathVariable String query, @RequestBody UserRequestDto requestDto) {
        return itemService.postCall(query, requestDto);
    }

    // [4]서버 - 헤더의 토큰, 바디의 객체 받을 것 Dto 객체로 매개변수로 받음
    @PostMapping("/exchange-call")
    public ItemResponseDto exchangeCall(@RequestHeader("X-Authorization") String token, @RequestBody UserRequestDto requestDto) {
        return itemService.exchangeCall(token, requestDto);
    }
}