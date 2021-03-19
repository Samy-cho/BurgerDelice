package process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;
import data.Block;
import data.Checkout;
import data.Cook;
import data.Counter;
import data.Customer;
import data.Ingredient;
import data.Menu;
import data.Oven;

public class RestaurantManager {

	private List<Oven> ovens = new ArrayList<Oven>();
	private List<Checkout> checkouts = new ArrayList<Checkout>();
	private List<Counter> counters = new ArrayList<Counter>();

	private List<Customer> customers = new ArrayList<Customer>();
	private List<Cook> cooks = new ArrayList<Cook>();

	private List<Block> takenBlocks = new ArrayList<Block>();

	private List<Menu> menus = new ArrayList<Menu>();

	private HashMap<Integer, List<Ingredient>> orders = new HashMap<Integer, List<Ingredient>>();

	private int currentId;
	private double money = 0;

	public RestaurantManager() {
		super();
	}
	
	public void addMoney(double money) {
		this.money += money;
	}

	public void addOven(Oven oven) {
		ovens.add(oven);
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void addTakenBlock(Block block) {
		takenBlocks.add(block);
	}

	public void add(Integer id, List<Ingredient> ingredients) {
		orders.put(id, ingredients);
	}

	public void removeTakenBlock(Block block) {
		takenBlocks.remove(block);
	}

	public void removeOrder(int id) {
		orders.remove(id);
	}

	public List<Oven> getOvens() {
		return ovens;
	}

	public void setOvens(List<Oven> ovens) {
		this.ovens = ovens;
	}

	public List<Checkout> getCheckouts() {
		return checkouts;
	}

	public void setCheckouts(List<Checkout> checkouts) {
		this.checkouts = checkouts;
	}

	public List<Counter> getCounters() {
		return counters;
	}

	public void setCounters(List<Counter> counters) {
		this.counters = counters;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Cook> getCooks() {
		return cooks;
	}

	public void setCooks(List<Cook> cooks) {
		this.cooks = cooks;
	}

	public List<Block> getTakenBlocks() {
		return takenBlocks;
	}

	public void setTakenBlocks(List<Block> takenBlocks) {
		this.takenBlocks = takenBlocks;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public HashMap<Integer, List<Ingredient>> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Integer, List<Ingredient>> orders) {
		this.orders = orders;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String toString(Integer index) {
		String message = "N°" + index + " - Commande : ";
		for (Ingredient ingredient : orders.get(index)) {
			message += ingredient.getName() + " : " + ingredient.getNbByMenu() + " -- ";
		}
		return message;
	}

	public void generateCustomer() {
		if (!takenBlocks.contains(new Block(GameConfiguration.LINE_ENTRY, GameConfiguration.COLUMN_ENTRY))) {
			if (SimulationUtility.getRandom(0, 10) < 2) {
				Block block = new Block(GameConfiguration.LINE_ENTRY, GameConfiguration.COLUMN_ENTRY);
				Customer customer;
				if(SimulationUtility.getRandom(0, 10) < 3) {
					customer = new Customer(block, currentId, false, true);
				}
				else {
					customer = new Customer(block, currentId, false, false);
				}
				addCustomer(customer);
				addTakenBlock(block);
				currentId++;
			}

		}

	}

}
