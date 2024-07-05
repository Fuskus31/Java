package attaque;

public interface Orderable<T> extends Comparable<T> {
	
	default boolean isAfter(T other) {
		return compareTo(other) > 0;
	}
	
	default boolean isSameAs(T other) {
		return compareTo(other) == 0;
	}
	
	default boolean isBefore(T other) {
		return compareTo(other) < 0;
	}
}
