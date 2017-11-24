package lk.sliit.transport.publicTransportService.dtos;

import lk.sliit.transport.publicTransportService.models.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kashifroshen on 11/18/17.
 */
public class ChartDTO {
   private Date date;

    private  int count;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
