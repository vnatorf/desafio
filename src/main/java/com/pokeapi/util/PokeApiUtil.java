package com.pokeapi.util;

import java.util.Collection;
import java.util.Collections;

public final class PokeApiUtil {

	public static <T> Collection<T> emptyIfNull(Collection<T> collection) {
		return collection == null ? Collections.<T>emptyList() : collection;
	}

	public static Integer valueOf(final String valor) {
		try {
			return Integer.valueOf(valor);
		} catch (final NumberFormatException e) {
			return null;
		}
	}

}