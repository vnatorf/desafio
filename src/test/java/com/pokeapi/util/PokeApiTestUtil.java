package com.pokeapi.util;

import java.util.HashSet;
import java.util.Set;

public final class PokeApiTestUtil {

	public static Set<String> getSetString(final int total, final String concat) {
		if (total == 0) {
			return new HashSet<>();
		}

		final Set<String> pokemons = new HashSet<>();

		for (int i = 0; i < total; i++) {
			pokemons.add((i + 1) + (concat != null ? concat : ""));
		}

		return pokemons;
	}

}