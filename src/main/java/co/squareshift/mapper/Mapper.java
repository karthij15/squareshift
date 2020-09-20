package co.squareshift.mapper;

public interface Mapper<I, O> {

	O map(I i) throws MapperException;

}
