package ru.kscsq.lunch.util;

import org.springframework.http.HttpStatus;
import ru.kscsq.lunch.util.exception.ApplicationException;

import java.time.LocalTime;

public class VoteUtil {

    public static final String EXCEPTION_VOTING_RESTRICTION = "exception.vote.restrictionVote";

    private static LocalTime cutOffTime = LocalTime.of(11, 0);

    public static LocalTime getTime() {
        return cutOffTime;
    }

    public static void setTime(LocalTime limitTime) {
        VoteUtil.cutOffTime = limitTime;
    }

    public static void checkingTimeForSecondVote () {
        if (LocalTime.now().isAfter(VoteUtil.getTime())) {
            throw new ApplicationException(EXCEPTION_VOTING_RESTRICTION, HttpStatus.NOT_MODIFIED, VoteUtil.getTime().toString());
        }
    }
}
