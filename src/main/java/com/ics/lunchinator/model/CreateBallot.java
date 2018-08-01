package com.ics.lunchinator.model;

import lombok.Data;
import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author joshpowell
 */
@Data
public class CreateBallot {

  @NonNull
  public Date endTime;
  @NonNull
  public List<Voter> voters;


  public void setEndTime(String value) {

    SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yy hh:mm");
    try {
      endTime = formatter1.parse(value);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

}
