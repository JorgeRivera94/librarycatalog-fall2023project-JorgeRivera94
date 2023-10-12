package interfaces;

/**
 * Functional interface to filter books or users
 * that match the characteristics given by an instance
 * of this lambda function as a parameter.
 * @author jorge
 *
 * @param <E>
 */
@FunctionalInterface
public interface FilterFunction<E> {
	public boolean filter(E e);
}
