package co.squareshift.mapper.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.squareshift.mapper.Mapper;
import co.squareshift.mapper.MapperException;

public enum SeatSlotsMapper implements Mapper<String, List<List<Integer>>> {

	INSTANCE;
	private static ObjectMapper mapperInstance = new ObjectMapper();

	@Override
	@SuppressWarnings("unchecked")
	public List<List<Integer>> map(String i) throws MapperException {
		try {
			return mapperInstance.readValue(i, List.class);
		} catch (IOException e) {
			throw new MapperException(e.getMessage());
		}
	}
}
