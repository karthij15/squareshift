package co.squareshift.service.impl;

import java.util.List;

import co.squareshift.model.Seat;
import co.squareshift.model.request.SeatRequest;
import co.squareshift.model.response.SeatResponse;
import co.squareshift.service.SeatDecider;

public class DefaultSeatDecider implements SeatDecider<SeatRequest, SeatResponse> {

	@Override
	public SeatResponse process(SeatRequest request) {

		int counter = 1; // seat counter
		SeatResponse response = new SeatResponse();

		// Fill Array with default SeatMarkers
		request.markSeats();
		
		counter = markPassengers(request, counter, Seat.AISLE.getSeatMarker());
		counter = markPassengers(request, counter, Seat.WINDOW.getSeatMarker());
		markPassengers(request, counter, Seat.MIDDLE.getSeatMarker());

		// Due to pass by reference marked seats will be populated with passenger seat
		List<String[][]> slotsAllocated = request.getMarkedSlots();

		response.setSlots(slotsAllocated);
		response.setSuccessResponseCode();

		return response;
	}

	// TODO: reduce complexity
	private int markPassengers(SeatRequest request, int counter, String seatMarker) {

		int passenger = counter;
		int slotIdx = 0;
		int rowIdx = 0;
		int totalSlots = request.getTotalSlots();

		/*
		 * iterate every row of each slot and traverse all the node to find the seat
		 * marker if found allocate passenger seat. repeat the iteration until max row
		 * limit reached | passengers filled
		 */
		while (slotIdx < totalSlots) {

			String[][] slotsArr = request.getMarkedSlots().get(slotIdx);

			int row = slotsArr.length;
			int column = slotsArr[0].length;

			boolean endFlag = false;

			for (int r = rowIdx; r < row && !endFlag; r++) {

				for (int c = 0; c < column; c++) {

					if (seatMarker.equals(slotsArr[r][c]) && (passenger <= request.getPassengers())) {
						slotsArr[r][c] += Seat.getPassangerSeat(passenger);
						passenger++;

						if (request.isLastColumn(c, column)) {
							endFlag = true;
							break;
						}
					}
				}

				if (r == rowIdx)
					break;
			}

			if (request.isLastSlot(slotIdx)) {
				slotIdx = -1;
				rowIdx++;
			}

			if (rowIdx == request.getMaxRow())
				break;

			slotIdx++;
		}
		return passenger;
	}
}
