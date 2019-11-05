package com.pokeapi.util;

public final class ChaveApplicationPropertiesUtil {

	public final class SERVICE {

		public static final String CONNECT_TIMEOUT = "${service.connect.timeout}";

		public static final String READ_TIMEOUT = "${service.read.timeout}";

		public static final String POKEAPI_HOST = "${service.pokeapi.host}";

		public static final String POKEAPI_POKEMON_PATH = "${service.pokeapi.pokemon.path}";

		public static final String POKEAPI_EGG_GROUP_PATH = "${service.pokeapi.egggroup.path}";

	}

}