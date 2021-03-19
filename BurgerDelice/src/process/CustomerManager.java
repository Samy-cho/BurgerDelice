package process;

import java.util.List;
import java.util.Map.Entry;

import config.GameConfiguration;
import data.Block;
import data.Customer;
import data.Ingredient;
import data.Storage;
import data.StorageMap;

public class CustomerManager extends MoveCharacters {

	private StorageMap storageMapInstance = StorageMap.getInstance();

	private RestaurantManager restaurantManager;
	private MoveToEatOnTheRestaurant moveToEatOnTheRestaurant = new MoveToEatOnTheRestaurant();
	
	public CustomerManager(RestaurantManager restaurantManager) {
		this.restaurantManager = restaurantManager;
	}

	public void moveCustomer() {
		List<Customer> customers = restaurantManager.getCustomers();
		for (Customer customer : customers) {
			if (!customer.isWaitingOrder()) {
				moveCustomerBeforeOrder(customer);
			} else if (customer.isWaitingOrder()
					&& customer.getPosition().getX() == GameConfiguration.COLUMN_ORDER_RECEPTION
					&& customer.getTimeWaiting() < GameConfiguration.TIME_FOR_ORDER_RECEPTION) {
				customer.incrementWaitingTime();
			} else if (customer.getTimeWaiting() == GameConfiguration.TIME_FOR_ORDER_RECEPTION) {
				if(customer.isOnTheRestaurant()) {
					moveToFindTable(customer);
				}
				else{
					moveCustomerToExit(customer);
				}
			} else {
				moveCustomerToOrder(customer);
			}
		}
	}

	private void moveCustomerBeforeOrder(Customer customer) {
		Block block = customer.getPosition();

		moveTopCollision(block, restaurantManager);

		if (customer.getPosition().getY() - GameConfiguration.LINE_ORDER == 0 && customer.isWaitingOrder() == false) {
			addOrder(customer);

			customer.setWaitingOrder(true);
		}
	}

	private void moveCustomerToOrder(Customer customer) {
		if (customer.getPosition().getX() > GameConfiguration.COLUMN_ORDER_RECEPTION) {
			moveLeftCollision(customer.getPosition(), restaurantManager);
		}
	}

	private void moveCustomerToExit(Customer customer) {
		moveLeftCollision(customer.getPosition(), restaurantManager);

		restaurantManager.removeOrder(customer.getId());
	}

	private void addOrder(Customer customer) {
		List<Ingredient> ingredients = restaurantManager.getMenus().get(customer.getOrder()).getIngredients();

		restaurantManager.add(customer.getId(), restaurantManager.getMenus().get(customer.getOrder()).getIngredients());

		for (Ingredient ingredient : ingredients) {
			for (Entry<String, Storage> storage : storageMapInstance.getIngredientsStorage().entrySet()) {
				if (ingredient.getName().equals(storage.getKey())) {
					storage.getValue().decrementCapacity(ingredient.getNbByMenu());
					restaurantManager.addMoney(SimulationUtility.lookingForPrice(ingredient.getName()) * ingredient.getNbByMenu());
				}
			}
		}
	}
	
	private void moveToFindTable(Customer customer) {
		moveToEatOnTheRestaurant.eatOnTable(customer, restaurantManager);
//		moveDownCollision(customer.getPosition(), restaurantManager);

		restaurantManager.removeOrder(customer.getId());
	}
	
	
}