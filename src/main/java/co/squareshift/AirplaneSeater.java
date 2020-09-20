package co.squareshift;

import java.util.List;
import java.util.Scanner;

import co.squareshift.mapper.impl.SeatSlotsMapper;
import co.squareshift.model.request.InvalidPassengerException;
import co.squareshift.model.request.SeatRequest;
import co.squareshift.model.response.ApplicationException;
import co.squareshift.model.response.SeatResponse;
import co.squareshift.service.SeatDecider;
import co.squareshift.service.impl.DefaultSeatDecider;

public class AirplaneSeater {

	SeatRequest seatRequest;

	AirplaneSeater(SeatRequest request) {
		this.seatRequest = request;
	}

	SeatResponse processSeating() {
		SeatDecider<SeatRequest, SeatResponse> service = new DefaultSeatDecider();
		return service.process(this.seatRequest);
	}

	public static void main(String... args) {

		Scanner inputReader = null;

		try {
			/*
			 * Instead UI Layer - User interaction maintained through traditional SYSOUT
			 */

			System.out.println(
					"Enter expected rows and column of seats in an enclosed square braces sepeated by comma. \n"
							+ "Group the slots by square braces and seperated by comma. \n"
							+ "Example: [[3,2],[4,3],[2,3],[3,4]]");

			inputReader = new Scanner(System.in);
			String userInputSlots = inputReader.next();

			List<List<Integer>> userInputSlotsList = SeatSlotsMapper.INSTANCE.map(userInputSlots);

			System.out.println("Enter Number of Passangers waiting in Queue. \n" + "Example: 30");

			int numberOfPassangers = inputReader.nextInt();

			SeatRequest request = new SeatRequest();
			request.setSeatSlots(userInputSlotsList);
			request.setPassengers(numberOfPassangers);

			if (request.isValidPassengers()) {
				throw new InvalidPassengerException("Invalid Passengers: It should be less than total seat count");
			}

			AirplaneSeater application = new AirplaneSeater(request);
			SeatResponse response = application.processSeating();

			if(response.isValid())
				response.printSeats();
			else
				throw new ApplicationException("Error while processing Seating " + response.getResponseCode());

		} catch (Exception e) {
			System.out.println(e.getMessage());

			if (inputReader != null) {
				inputReader.close();
			}
		} finally {
			if (inputReader != null) {
				inputReader.close();
			}
		}

	}
}
