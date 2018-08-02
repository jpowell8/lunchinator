package com.ics.lunchinator.service;

import com.ics.lunchinator.model.CreateBallot;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static com.ics.lunchinator.service.LunchinatorService.isTimeValid;

/**
 * @author joshpowell
 */
public class LunchinatorServiceTest {


  @Test
  public void isTimeValid_past_false(){
    Date past = new Date(System.currentTimeMillis() - Calendar.HOUR);

    CreateBallot ballot = new CreateBallot(past, Lists.emptyList());

    Assert.assertFalse(isTimeValid(ballot));
  }

  @Test
  public void isTimeValid_future_true(){
    Date future = new Date(System.currentTimeMillis() + Calendar.HOUR);

    CreateBallot ballot = new CreateBallot(future, Lists.emptyList());

    Assert.assertTrue(isTimeValid(ballot));
  }

}
