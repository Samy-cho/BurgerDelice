package engine.process;

import org.apache.log4j.Logger;

import config.GameConfiguration;
import engine.map.Block;
import engine.mobile.Customer;
import gui.MainGUI;
import log.LoggerUtility;

public class ManageCustomer extends MoveCharacters {
	private Logger logger = LoggerUtility.getLogger(ManageCustomer.class, "text");

	public ManageCustomer() {

	}

	/**
	 * Do the movement of all customers in the restaurant.
	 */
	public void movementCustomer() {
		for (Customer customer : MainGUI.manager.getCustomers()) {
			if (customer.isWaitingOrder() == false) {
				moveCustomerBeforeOrder(customer);
			} else if (customer.isWaitingOrder() == true
					&& customer.getPosition().getColumn() == GameConfiguration.COLUMN_ORDER_RECEPTION
					&& customer.getTimeWaiting() < 5) {
				customer.incrementWaitingTime();
			} else if (customer.getTimeWaiting() == 5) {
				moveCustomerToTheExit(customer);
			} else {
				moveCustomerToOrder(customer);
			}
		}

	}

	/**
	 * This allow to manage the move of customer, we collisions are avoided.
	 */
	public void moveCustomerBeforeOrder(Customer customer) {
		Block customerBlock = customer.getPosition();
		logger.info(customerBlock);
		moveTopCollisionFree(customerBlock, MainGUI.manager.getTakenBlocks());

		// Choose the menu randomly.
		if (customer.getPosition().getLine() - GameConfiguration.LINE_ORDER == 0
				&& customer.getPosition().getColumn() == GameConfiguration.COLUMN_ORDER_1
				&& customer.isWaitingOrder() == false) {
			addOrder(customer);
			customer.setWaitingOrder(true);
		}
		//
		if (customer.isWaitingOrder() == true) {
			if (!(customer.getPosition().getColumn() == GameConfiguration.COLUMN_ORDER_1 - 5)) {
				MainGUI.manager.remove(customerBlock);
				logger.info(customerBlock);
				MainGUI.manager.add(customerBlock);
			}
		}
	}

	private void moveCustomerToOrder(Customer customer) {
		// TODO Auto-generated method stub
		if (customer.getPosition().getColumn() > GameConfiguration.COLUMN_ORDER_RECEPTION) {
			moveLeftCollisionFree(customer.getPosition(), MainGUI.manager.getTakenBlocks());
		}
	}

	private void moveCustomerToTheExit(Customer customer) {
		// TODO Auto-generated method stub
		moveLeftCollisionFree(customer.getPosition(), MainGUI.manager.getTakenBlocks());
		MainGUI.manager.remove(customer.getId());
	}

	public void addOrder(Customer customer) {
		MainGUI.manager.add(customer.getId(), MainGUI.manager.getMenus().get(customer.getOrder()).getIngredients());
	}
}
