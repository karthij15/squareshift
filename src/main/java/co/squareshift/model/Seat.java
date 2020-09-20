package co.squareshift.model;

public enum Seat {

	AISLE(Constants.SEAT_MARKER_AISLE), WINDOW(Constants.SEAT_MARKER_WINDOW), MIDDLE(Constants.SEAT_MARKER_MIDDLE);

	private final String seatMarker;

	private Seat(String seatMarker) {
		this.seatMarker = seatMarker;
	}

	public String getSeatMarker() {
		return seatMarker;
	}

	private static class Constants {
		private static final String SEAT_MARKER_AISLE = "A";
		private static final String SEAT_MARKER_WINDOW = "W";
		private static final String SEAT_MARKER_MIDDLE = "M";
	}

	public static boolean isUnAllocatedSet(String seatMarker) {
		return seatMarker.length() == 1;
	}

	//add leading zero for formatting the result
	public static String getPassangerSeat(int i) {
		return String.format("%02d", i);
	}
}
