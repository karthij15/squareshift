package co.squareshift.model.response;

import java.util.ArrayList;
import java.util.List;

import co.squareshift.model.Constants;
import co.squareshift.model.Seat;

public class SeatResponse {

	private static final int RESPONSE_CODE_SUCCESS = 0;
	private static final int RESPONSE_CODE_FAILURE = 999;

	int responseCode;
	List<String[][]> slots = new ArrayList<>();

	public SeatResponse() {
		this.responseCode = RESPONSE_CODE_FAILURE;
	}

	public void setSuccessResponseCode() {
		this.responseCode = RESPONSE_CODE_SUCCESS;
	}

	public boolean isValid() {
		return this.responseCode != RESPONSE_CODE_FAILURE;
	}

	public int getResponseCode() {
		return this.responseCode;
	}

	public void setSlots(List<String[][]> slots) {
		this.slots = slots;
	}

	public void printSeats() {

		for (String[][] slotsArr : slots) {

			System.out.println("------------------------------");

			for (int row = 0; row < slotsArr.length; row++) {
				for (int column = 0; column < slotsArr[row].length; column++) {
					if (Seat.isUnAllocatedSet(slotsArr[row][column]))
						System.out.print(Constants.UNALLOCATED_SEAT);
					else
						System.out.print(slotsArr[row][column]);

					System.out.print(Constants.SEAT_SEPERATOR);
				}

				System.out.println();
			}

			System.out.println("------------------------------");
		}

	}
}
