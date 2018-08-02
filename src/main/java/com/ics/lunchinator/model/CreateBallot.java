package com.ics.lunchinator.model;

import lombok.Data;
import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author joshpowell
 */
@Data
public class CreateBallot {

  @NonNull
  public Date endTime;
  @NonNull
  public List<Voter> voters;

  public Date getEndTime() {
    if(endTime == null) {
      endTime = getDefaultTime();
    }
    return endTime;
  }

  public void setEndTime(String value) {
    if (isEmpty(value)) {
      endTime = getDefaultTime();
      return;
    }

    SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yy hh:mm");
    try {
      endTime = formatter1.parse(value);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private Date getDefaultTime() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY,11);
    cal.set(Calendar.MINUTE,30);

    Date timeToRun = cal.getTime();

    if(System.currentTimeMillis() > timeToRun.getTime()){
      cal.add(Calendar.DATE,1);
    }
    return cal.getTime();
  }

}
