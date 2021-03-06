package ru.kscsq.lunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kscsq.lunch.AuthorizedUser;
import ru.kscsq.lunch.model.Vote;
import ru.kscsq.lunch.service.VoteService;

import java.net.URI;

@RestController
@RequestMapping(value = VoteProfileRestController.REST_URL)
public class VoteProfileRestController {
    private static final Logger log = LoggerFactory.getLogger(VoteProfileRestController.class);

    static final String REST_URL = "/rest/profile/restaurants";

    private VoteService service;

    @Autowired
    public VoteProfileRestController(VoteService service) {
        this.service = service;
    }

    @PostMapping(value = "/{id}/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable("id") int restaurantId) {
        log.info("Vote for a restaurant " + restaurantId);

        int userId = AuthorizedUser.id();
        Vote vote = service.vote(restaurantId, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(vote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }
}
