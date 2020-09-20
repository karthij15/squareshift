package co.squareshift.service;

import co.squareshift.model.request.SeatRequest;
import co.squareshift.model.response.SeatResponse;

public interface SeatDecider<I extends SeatRequest, O extends SeatResponse> {

	O process(I request);
}
