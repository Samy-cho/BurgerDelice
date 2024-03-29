package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.GameConfiguration;
import data.Storage;
import data.StorageMap;
import process.RestaurantManager;

public class StorageDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Name of each ingredient display in window.
	 */
	protected JLabel steakLabel = new JLabel("Steak");
	protected JLabel chickenLabel = new JLabel("Poulet pane");
	protected JLabel fishLabel = new JLabel("Fish");
	protected JLabel breadLabel = new JLabel("Pain");
	protected JLabel cheeseLabel = new JLabel("Cheddar");
	protected JLabel tomatoLabel = new JLabel("Tomate");
	protected JLabel saladLabel = new JLabel("Salade");
	protected JLabel onionLabel = new JLabel("Oignon");
	protected JLabel cornichonLabel = new JLabel("Cornichon");
	protected JLabel chipsLabel = new JLabel("Frites moyenne");
	protected JLabel sauceLabel = new JLabel("Sauce");

	/**
	 * Current quantity of each ingredient display in window.
	 */
	protected JLabel steakQuantityLabel = new JLabel();
	protected JLabel chickenQuantityLabel = new JLabel();
	protected JLabel fishQuantityLabel = new JLabel();
	protected JLabel breadQuantityLabel = new JLabel();
	protected JLabel cheeseQuantityLabel = new JLabel();
	protected JLabel tomatoQuantityLabel = new JLabel();
	protected JLabel saladQuantityLabel = new JLabel();
	protected JLabel onionQuantityLabel = new JLabel();
	protected JLabel cornichonQuantityLabel = new JLabel();
	protected JLabel chipsQuantityLabel = new JLabel();
	protected JLabel sauceQuantityLabel = new JLabel();

	/**
	 * Buttons to add quantity to basket.
	 */
	protected MyButton addSteak = new MyButton("Ajouter");
	protected MyButton addChicken = new MyButton("Ajouter");
	protected MyButton addFish = new MyButton("Ajouter");
	protected MyButton addBread = new MyButton("Ajouter");
	protected MyButton addCheese = new MyButton("Ajouter");
	protected MyButton addTomato = new MyButton("Ajouter");
	protected MyButton addSalad = new MyButton("Ajouter");
	protected MyButton addOnion = new MyButton("Ajouter");
	protected MyButton addCornichon = new MyButton("Ajouter");
	protected MyButton addChips = new MyButton("Ajouter");
	protected MyButton addSauce = new MyButton("Ajouter");

	/**
	 * Buttons to remove quantity to basket.
	 */
	protected MyButton removeSteak = new MyButton("Retirer");
	protected MyButton removeChicken = new MyButton("Retirer");
	protected MyButton removeFish = new MyButton("Retirer");
	protected MyButton removeBread = new MyButton("Retirer");
	protected MyButton removeCheese = new MyButton("Retirer");
	protected MyButton removeTomato = new MyButton("Retirer");
	protected MyButton removeSalad = new MyButton("Retirer");
	protected MyButton removeOnion = new MyButton("Retirer");
	protected MyButton removeCornichon = new MyButton("Retirer");
	protected MyButton removeChips = new MyButton("Retirer");
	protected MyButton removeSauce = new MyButton("Retirer");

	private StorageMap storageMapInstance = StorageMap.getInstance();

	private Dimension preferredSize = new Dimension(500, 300);

	private RestaurantManager restaurantManager;
	private BuyDisplay buyDisplay;
	private int storageIndex = 0;

	public StorageDisplay(RestaurantManager restaurantManager, BuyDisplay buyDisplay) {
		this.restaurantManager = restaurantManager;
		this.buyDisplay = buyDisplay;
		initStoragePanel();
	}

	/**
	 * Initialize the display about storage in the restaurant.
	 */
	public void initStoragePanel() {

		setPreferredSize(preferredSize);
		GridLayout grid = new GridLayout(12, 4);
		grid.setVgap(1);
		setLayout(grid);
		setBackground(Color.WHITE);
		add(new JLabel("Nom"));
		add(new JLabel("Quantité"));
		add(new JLabel("Acheter"));
		add(new JLabel("Retirer"));

		addActionForChangeStorage(steakLabel, steakQuantityLabel, addSteak, removeSteak);
		addActionForChangeStorage(chickenLabel, chickenQuantityLabel, addChicken, removeChicken);
		addActionForChangeStorage(fishLabel, fishQuantityLabel, addFish, removeFish);
		addActionForChangeStorage(breadLabel, breadQuantityLabel, addBread, removeBread);
		addActionForChangeStorage(saladLabel, saladQuantityLabel, addSalad, removeSalad);
		addActionForChangeStorage(tomatoLabel, tomatoQuantityLabel, addTomato, removeTomato);
		addActionForChangeStorage(cheeseLabel, cheeseQuantityLabel, addCheese, removeCheese);
		addActionForChangeStorage(onionLabel, onionQuantityLabel, addOnion, removeOnion);
		addActionForChangeStorage(cornichonLabel, cornichonQuantityLabel, addCornichon, removeCornichon);
		addActionForChangeStorage(chipsLabel, chipsQuantityLabel, addChips, removeChips);
		addActionForChangeStorage(sauceLabel, sauceQuantityLabel, addSauce, removeSauce);

		setVisible(true);
	}
	
	private void addActionForChangeStorage(JLabel nameLabel, JLabel quantityLabel, 
			MyButton addButton, MyButton removeButton) {
		addStorageLabel(nameLabel, quantityLabel);
		add(addButton);
		add(removeButton);
		addButton.addActionListener(new AddToBasket(storageIndex));
		removeButton.addActionListener(new RemoveFromBasket(storageIndex));
		storageIndex++;
	}

	/**
	 * Add the labels for do the display of ingredients storage.
	 * 
	 * @param jLabel           name of ingredient
	 * @param quantityLabel    actually quantity of one ingredient
	 * @param quantityMaxLabel quantity maximum of one ingredient
	 */
	private void addStorageLabel(JLabel jLabel, JLabel quantityLabel) {
		add(jLabel);
		jLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.gray));
		quantityLabel.setText(
				String.valueOf(storageMapInstance.getIngredientToStorage(jLabel.getText()).getCurrentCapacity()));
		add(quantityLabel);
		quantityLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.gray));

//		logger.info(jLabel.getText());
	}

	/**
	 * Allows to display an ingredient in the storage, his quantity and also his
	 * maximum quantity.
	 * 
	 * @param nameOfIngredient which in the storage
	 * @param quantityLabel    quantity of an ingredient in the storage
	 * @param quantityMaxLabel quantity maximum of an ingredient that storage can
	 *                         get
	 */
	private void updateStorageIngredient(JLabel quantityLabel, String nameIngredient) {
		Storage storage = storageMapInstance.getIngredientToStorage(nameIngredient);
		quantityLabel.setText(
				String.valueOf(storage.getCurrentCapacity()) + " / " + String.valueOf(storage.getCapacityMax()));
	}

	/**
	 * Allows to see the decrease in stock as the orders take by customers or
	 * increase when one delivery is arrived.
	 */
	public void updateStorageDisplay() {

		updateStorageIngredient(steakQuantityLabel, GameConfiguration.INGREDIENT[0]);
		updateStorageIngredient(chickenQuantityLabel, GameConfiguration.INGREDIENT[1]);
		updateStorageIngredient(fishQuantityLabel, GameConfiguration.INGREDIENT[2]);
		updateStorageIngredient(breadQuantityLabel, GameConfiguration.INGREDIENT[3]);
		updateStorageIngredient(saladQuantityLabel, GameConfiguration.INGREDIENT[4]);
		updateStorageIngredient(cornichonQuantityLabel, GameConfiguration.INGREDIENT[5]);
		updateStorageIngredient(cheeseQuantityLabel, GameConfiguration.INGREDIENT[6]);
		updateStorageIngredient(onionQuantityLabel, GameConfiguration.INGREDIENT[7]);
		updateStorageIngredient(tomatoQuantityLabel, GameConfiguration.INGREDIENT[8]);
		updateStorageIngredient(chipsQuantityLabel, GameConfiguration.INGREDIENT[9]);
		updateStorageIngredient(sauceQuantityLabel, GameConfiguration.INGREDIENT[10]);

	}

	private class AddToBasket implements ActionListener {

		private int number;

		public AddToBasket(int number) {
			this.number = number;
		}

		public void actionPerformed(ActionEvent e) {
			restaurantManager.addBasket(GameConfiguration.INGREDIENT[number]);
			buyDisplay.updateBasket();
		}
	}

	private class RemoveFromBasket implements ActionListener {

		private int number;

		public RemoveFromBasket(int number) {
			this.number = number;
		}

		public void actionPerformed(ActionEvent e) {
			restaurantManager.removeBasket(GameConfiguration.INGREDIENT[number]);
			buyDisplay.updateBasket();
		}
	}

}
