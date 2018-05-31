package ru.kscsq.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.kscsq.lunch.model.Vote;
import ru.kscsq.lunch.util.VoteUtil;
import ru.kscsq.lunch.util.exception.NotFoundException;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import static ru.kscsq.lunch.RestaurantTestData.RESTAURANT2_ID;
import static ru.kscsq.lunch.RestaurantTestData.RESTAURANT3_ID;
import static ru.kscsq.lunch.UserTestData.ADMIN_ID;
import static ru.kscsq.lunch.UserTestData.USER_ID;
import static ru.kscsq.lunch.VoteTestData.assertMatch;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void testBeforeCloseOfVoting() throws Exception {
        Date date = new Date();

        VoteUtil.setTime(LocalTime.now().plusHours(1));
        Vote newVote = service.vote(RESTAURANT3_ID, USER_ID);
        newVote.setRestaurantId(RESTAURANT3_ID);
        newVote.setUserId(USER_ID);
        assertMatch(service.get(date, USER_ID), newVote);
    }

    @Test
    public void testAfterVotingIsClosed() throws Exception {
        Date date = new Date();

        VoteUtil.setTime(LocalTime.now().plusHours(-1));
        Vote newVote = service.vote(RESTAURANT3_ID, USER_ID);
        newVote.setRestaurantId(RESTAURANT3_ID);
        newVote.setUserId(USER_ID);
        assertMatch(service.get(date, USER_ID), newVote);
    }

    @Test
    public void testReVote() throws Exception {
        Date date = new Date();
        VoteUtil.setTime(LocalTime.now().plusHours(1));

        service.vote(RESTAURANT2_ID, USER_ID);
        Vote secondVote = service.vote(RESTAURANT3_ID, USER_ID);
        secondVote.setRestaurantId(RESTAURANT3_ID);
        secondVote.setUserId(USER_ID);

        assertMatch(service.get(date, USER_ID), secondVote);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(new Date(), 1);
    }

    @Test
    public void testGet() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-05-07");
        Assert.notNull(service.get(date, ADMIN_ID), "vote not found");
    }
}
