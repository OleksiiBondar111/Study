package com.microservices.api.AlbumsResourceServer.controller;


import com.microservices.api.AlbumsResourceServer.model.AlbumResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/albums")
@Slf4j
public class AlbumsController {

    @GetMapping
    List<AlbumResponseModel> getAlbums(){
        List<AlbumResponseModel> returnValue = new ArrayList<>();

        AlbumResponseModel albumEntity = new AlbumResponseModel();
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription("album 1 description");
        albumEntity.setName("album 1 name");

        AlbumResponseModel albumEntity2 = new AlbumResponseModel();
        albumEntity2.setAlbumId("album2Id");
        albumEntity2.setDescription("album 2 description");
        albumEntity2.setName("album 2 name");

        returnValue.add(albumEntity);
        returnValue.add(albumEntity2);

        log.info("GET call: {}", returnValue);

        return returnValue;
    }



}
