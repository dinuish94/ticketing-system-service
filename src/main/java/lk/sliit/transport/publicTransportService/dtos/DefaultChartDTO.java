package lk.sliit.transport.publicTransportService.dtos;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kashifroshen on 11/19/17.
 */
public class DefaultChartDTO {
    List<BarChartDTO> yesterdayRecords;
    List<Object[]> startBusStopRecords;
    List<Object[]> endBusStopRecords;

    public List<BarChartDTO> getYesterdayRecords() {
        return yesterdayRecords;
    }

    public void setYesterdayRecords(List<BarChartDTO> yesterdayRecords) {
        this.yesterdayRecords = yesterdayRecords;
    }

    public List<Object[]> getStartBusStopRecords() {
        return startBusStopRecords;
    }

    public void setStartBusStopRecords(List<Object[]> startBusStopRecords) {
        this.startBusStopRecords = startBusStopRecords;
    }

    public List<Object[]> getEndBusStopRecords() {
        return endBusStopRecords;
    }

    public void setEndBusStopRecords(List<Object[]> endBusStopRecords) {
        this.endBusStopRecords = endBusStopRecords;
    }
}
