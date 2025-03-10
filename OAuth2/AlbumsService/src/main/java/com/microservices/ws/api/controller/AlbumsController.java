package com.microservices.ws.api.controller;

import com.microservices.ws.api.jwt.CustomJwt;
import com.microservices.ws.api.model.AlbumResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/albums")
@Slf4j
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET}
)
public class AlbumsController {

    @GetMapping
    List<AlbumResponseModel> getAlbums() {

        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        AlbumResponseModel albumEntity = new AlbumResponseModel();
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription(MessageFormat.format("Hello fullstack master {0} {1}, how is it going today?", jwt.getFirstName(), jwt.getLastName()));
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