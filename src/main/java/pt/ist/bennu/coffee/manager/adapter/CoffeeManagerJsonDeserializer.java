package pt.ist.bennu.coffee.manager.adapter;

import pt.ist.bennu.bennu.core.rest.mapper.AbstractJsonDeserializer;
import pt.ist.bennu.coffee.manager.domain.CoffeeItem;

public class CoffeeManagerJsonDeserializer extends AbstractJsonDeserializer {

	public void updateObject(CoffeeItem coffeeItem, String jsonString, String externalId) {

	}

	@Override
	public <T> T deserialize(String jsonString, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
