package Albums.microservice.ui.controllers;

import Albums.microservice.data.AlbumEntity;
import Albums.microservice.service.AlbumsService;
import Albums.microservice.ui.model.AlbumResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{id}/albums")
@RequiredArgsConstructor
@Slf4j
public class AlbumsController {

   private final AlbumsService albumsService;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            })
   public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);

        if(albumsEntities == null || albumsEntities.isEmpty())
        {
            return returnValue;
        }

        Type listType = new TypeToken<List<AlbumResponseModel>>(){}.getType();

        returnValue = new ModelMapper().map(albumsEntities, listType);
        log.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }
}
