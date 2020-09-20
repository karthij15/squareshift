package co.squareshift.model.request;

import java.util.ArrayList;
import java.util.List;

import co.squareshift.model.Seat;

public class SeatRequest {

	public static final int INDEXROW = 1;
	public static final int INDEXCOLUMN = 0;

	private List<List<Integer>> seatSlots;
	private int passengers;
	private int totalSlots;

	// from java 1.7 only after calling add method default memory allocation will
	// happen
	private List<String[][]> markedSlots = new ArrayList<>();

	public void setSeatSlots(List<List<Integer>> seatSlots) {
		this.seatSlots = seatSlots;
		setTotalSlots();
	}

	public void setPassengers(int passangers) {
		this.passengers = passangers;
	}

	public int getPassengers() {
		return this.passengers;
	}

	public List<String[][]> getMarkedSlots() {
		return this.markedSlots;
	}

	public void setTotalSlots() {
		this.totalSlots = this.seatSlots.size();
	}

	public int getTotalSlots() {
		return this.totalSlots;
	}

	public boolean isValidPassengers() {
		int seatCount = 0;

		for (List<Integer> slot : this.seatSlots) {
			 // get total number of seats from the provided input spec (row * column)
			seatCount += slot.stream().reduce(1, (row, column) -> row * column);
		}

		return this.passengers > seatCount;
	}

	/*
	 * input list of row x column specifications will be transformed to 2D array here.
	 * array will be filled with default seat marker
	 * seatSlots will be converted to markedSlots (from List<List<Integer>> to List<String[][]>)
	 */
	//TODO: reduce complexity
	public void markSeats() {
		int slot = 0;

		while (slot < this.totalSlots) {
			List<Integer> slotSpecs = this.seatSlots.get(slot);

			int row = slotSpecs.get(INDEXROW);
			int column = slotSpecs.get(INDEXCOLUMN);

			String[][] slotArr = new String[row][column];

			for (int r = 0; r < row; r++) {

				for (int c = 0; c < column; c++) {

					String charToFill = Seat.MIDDLE.getSeatMarker(); //Fill middle seat by default

					if (isFirstColumn(c) || isLastColumn(c, column))
						charToFill = Seat.AISLE.getSeatMarker();

					if ((isFirstSlot(slot) && isFirstColumn(c)) || (isLastSlot(slot) && isLastColumn(c, column)))
						charToFill = Seat.WINDOW.getSeatMarker();

					slotArr[r][c] = charToFill;
				}
			}

			this.markedSlots.add(slotArr);

			slot++;
		}

	}

	private boolean isFirstColumn(int c) {
		return c ==  0;
	}

	public boolean isLastColumn(int idx, int column) {
		return  (idx == (column - 1));
	}

	private boolean isFirstSlot(int slot) {
		return slot == 0;
	}
	
	public boolean isLastSlot(int slot) {
		return slot == (this.totalSlots - 1);
	}

	/*
	 * decide which slot has max row value
	 * and return the max row value
	 */
	public int getMaxRow() {
		int maxRow = 0;

		for (String[][] arr : this.markedSlots) {
			maxRow = Math.max(maxRow, arr.length);
		}

		return maxRow;
	}
}
