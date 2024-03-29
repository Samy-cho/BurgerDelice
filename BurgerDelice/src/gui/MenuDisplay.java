package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Ingredient;
import process.RestaurantManager;

public class MenuDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel menuLabel = new JLabel("Composition");
	private JLabel quantityLabel = new JLabel("Quantit�");
	private JLabel addLabel = new JLabel("Ajouter");
	private JLabel removeLabel = new JLabel("Retirer");

	/*
	 * Buttons for choose the menu to give at customer.
	 */
	private JButton changeFirstMenu = new JButton("Menu 1");
	private JButton changeSecondMenu = new JButton("Menu 2");
	private JButton changeThirdMenu = new JButton("Menu 3");

	/*
	 * Ingredients name in the menu.
	 */
	private JLabel ingredientLabel = new JLabel("Ingredient");
	private JLabel meatLabel = new JLabel();
	private JLabel breadLabel = new JLabel();
	private JLabel cheeseLabel = new JLabel();
	private JLabel tomatoLabel = new JLabel();
	private JLabel saladLabel = new JLabel();
	private JLabel onionLabel = new JLabel();
	private JLabel chipsLabel = new JLabel();
	private JLabel cornichonLabel = new JLabel();
	private JLabel sauceLabel = new JLabel();

	/*
	 * Quantity of each ingredients in the table of display.
	 */
	private JLabel meatQuantityLabel = new JLabel();
	private JLabel breadQuantityLabel = new JLabel();
	private JLabel cheeseQuantityLabel = new JLabel();
	private JLabel tomatoQuantityLabel = new JLabel();
	private JLabel saladQuantityLabel = new JLabel();
	private JLabel onionQuantityLabel = new JLabel();
	private JLabel cornichonQuantityLabel = new JLabel();
	private JLabel chipsQuantityLabel = new JLabel();
	private JLabel sauceQuantityLabel = new JLabel();

	/*
	 * Allows to increment specific ingredient in the menu panel.
	 */
	private MyButton addMeatButton = new MyButton("Ajouter");
	private MyButton addBreadButton = new MyButton("Ajouter");
	private MyButton addCheeseButton = new MyButton("Ajouter");
	private MyButton addTomatoButton = new MyButton("Ajouter");
	private MyButton addSaladButton = new MyButton("Ajouter");
	private MyButton addOnionButton = new MyButton("Ajouter");
	private MyButton addCornichonButton = new MyButton("Ajouter");
	private MyButton addChipsButton = new MyButton("Ajouter");
	private MyButton addSauceButton = new MyButton("Ajouter");

	/*
	 * Allows to decrement specific ingredient in the menu panel.
	 */
	private MyButton removeMeatButton = new MyButton("Retirer");
	private MyButton removeBreadButton = new MyButton("Retirer");
	private MyButton removeCheeseButton = new MyButton("Retirer");
	private MyButton removeTomatoButton = new MyButton("Retirer");
	private MyButton removeSaladButton = new MyButton("Retirer");
	private MyButton removeOnionButton = new MyButton("Retirer");
	private MyButton removeCornichonButton = new MyButton("Retirer");
	private MyButton removeChipsButton = new MyButton("Retirer");
	private MyButton removeSauceButton = new MyButton("Retirer");

	private int currentMenu = 0;
	private int ingredientIndex = 0;
	private List<Ingredient> ingredientsInMenu;

	private RestaurantManager restaurantManager;

	private Dimension preferredSize = new Dimension(500, 250);

	public MenuDisplay(RestaurantManager restaurantManager) {

		this.restaurantManager = restaurantManager;

		ingredientsInMenu = restaurantManager.getMenus().get(currentMenu).getIngredients();

		initStyleMenu();
		initLayout();
		initActions();

		printFirstMenu();
	}

	/**
	 * Initialize the table which display the menu and his ingredients, there is
	 * border between elements.
	 */
	private void initStyleMenu() {

		int alignement = (SwingConstants.CENTER);

		makeStyleIngredientInMenuPanel(menuLabel, alignement);
		makeStyleIngredientInMenuPanel(ingredientLabel, alignement);
		makeStyleIngredientInMenuPanel(addLabel, alignement);
		makeStyleIngredientInMenuPanel(removeLabel, alignement);
		makeStyleIngredientInMenuPanel(quantityLabel, alignement);

		/*
		 * Do the style for the name of each ingredient.
		 */
		makeStyleIngredientInMenuPanel(meatLabel, alignement);
		makeStyleIngredientInMenuPanel(cornichonLabel, alignement);
		makeStyleIngredientInMenuPanel(breadLabel, alignement);
		makeStyleIngredientInMenuPanel(cheeseLabel, alignement);
		makeStyleIngredientInMenuPanel(tomatoLabel, alignement);
		makeStyleIngredientInMenuPanel(saladLabel, alignement);
		makeStyleIngredientInMenuPanel(onionLabel, alignement);
		makeStyleIngredientInMenuPanel(chipsLabel, alignement);
		makeStyleIngredientInMenuPanel(sauceLabel, alignement);

		/*
		 * Do the style for the quantity of each ingredient.
		 */
		makeStyleIngredientInMenuPanel(meatQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(breadQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(cheeseQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(cornichonQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(tomatoQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(saladQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(onionQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(chipsQuantityLabel, alignement);
		makeStyleIngredientInMenuPanel(sauceQuantityLabel, alignement);

		/*
		 * Do style for menu button, when the menu is selected, his background change.
		 */
		changeFirstMenu.setBorderPainted(false);
		changeFirstMenu.setContentAreaFilled(false);
		changeFirstMenu.setBackground(Color.LIGHT_GRAY);
		changeFirstMenu.setOpaque(true);

		changeSecondMenu.setBorderPainted(false);
		changeSecondMenu.setContentAreaFilled(false);

		changeThirdMenu.setBorderPainted(false);
		changeThirdMenu.setContentAreaFilled(false);
	}

	/**
	 * 
	 * Make style an ingredient in the menu panel of the window with border and
	 * orientation.
	 * 
	 * @param jLabel get the text or quantity about an ingredient
	 * @param make   a center alignment
	 */
	private void makeStyleIngredientInMenuPanel(JLabel jLabel, int alignement) {
		jLabel.setHorizontalAlignment(alignement);
		jLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
	}

	/**
	 * Initialize the layout with the menu with his ingredients and this button.
	 */
	private void initLayout() {

		setPreferredSize(preferredSize);

		GridLayout grid = new GridLayout(11, 4);

		setLayout(grid);
		grid.setVgap(1);

		setBackground(Color.WHITE);
		add(menuLabel);
		add(changeFirstMenu);
		add(changeSecondMenu);
		add(changeThirdMenu);

		add(ingredientLabel);
		add(quantityLabel);
		add(addLabel);
		add(removeLabel);

		addInitMenuPanel(meatLabel, meatQuantityLabel, addMeatButton, removeMeatButton);
		addInitMenuPanel(breadLabel, breadQuantityLabel, addBreadButton, removeBreadButton);
		addInitMenuPanel(saladLabel, saladQuantityLabel, addSaladButton, removeSaladButton);
		addInitMenuPanel(cornichonLabel, cornichonQuantityLabel, addCornichonButton, removeCornichonButton);
		addInitMenuPanel(cheeseLabel, cheeseQuantityLabel, addCheeseButton, removeCheeseButton);
		addInitMenuPanel(onionLabel, onionQuantityLabel, addOnionButton, removeOnionButton);
		addInitMenuPanel(tomatoLabel, tomatoQuantityLabel, addTomatoButton, removeTomatoButton);
		addInitMenuPanel(chipsLabel, chipsQuantityLabel, addChipsButton, removeChipsButton);
		addInitMenuPanel(sauceLabel, sauceQuantityLabel, addSauceButton, removeSauceButton);

		setVisible(true);
	}

	private void printFirstMenu() {

		meatLabel.setText(String.valueOf(ingredientsInMenu.get(0).getName()));
		meatQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(0).getNbByMenu()));
		breadQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(1).getNbByMenu()));
		saladQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(2).getNbByMenu()));
		cornichonQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(3).getNbByMenu()));
		cheeseQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(4).getNbByMenu()));
		onionQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(5).getNbByMenu()));
		tomatoQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(6).getNbByMenu()));
		chipsQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(7).getNbByMenu()));
		sauceQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(8).getNbByMenu()));
	}

	/*
	 * Add all panel about one at table in order to display name of ingredient, the
	 * quantity of this and to allows modification of this quantity.
	 */
	private void addInitMenuPanel(JLabel jLabel, JLabel quantityLabel, JButton incrementButton,
			JButton decrementButton) {
		jLabel.setText(ingredientsInMenu.get(ingredientIndex).getName());
		add(jLabel);
		add(quantityLabel);
		add(incrementButton);
		add(decrementButton);
		ingredientIndex++;
	}

	/**
	 * Add all action for the menu which allows to increment or decrement one
	 * ingredient in the menu, and change menu to modify.
	 */
	private void initActions() {

		/*
		 * Action to show the menu.
		 */
		changeFirstMenu.addActionListener(new ShowMenu(0));
		changeSecondMenu.addActionListener(new ShowMenu(1));
		changeThirdMenu.addActionListener(new ShowMenu(2));

		/*
		 * Action to increment an ingredient in the menu.
		 */
		addMeatButton.addActionListener(new IncrementIngredient("Steak", meatQuantityLabel));
		addMeatButton.addActionListener(new IncrementIngredient("Poulet pane", meatQuantityLabel));
		addMeatButton.addActionListener(new IncrementIngredient("Fish", meatQuantityLabel));
		addBreadButton.addActionListener(new IncrementIngredient("Pain", breadQuantityLabel));
		addCheeseButton.addActionListener(new IncrementIngredient("Cheddar", cheeseQuantityLabel));
		addTomatoButton.addActionListener(new IncrementIngredient("Tomate", tomatoQuantityLabel));
		addSaladButton.addActionListener(new IncrementIngredient("Salade", saladQuantityLabel));
		addCornichonButton.addActionListener(new IncrementIngredient("Cornichon", cornichonQuantityLabel));
		addOnionButton.addActionListener(new IncrementIngredient("Oignon", onionQuantityLabel));
		addChipsButton.addActionListener(new IncrementIngredient("Frites moyenne", chipsQuantityLabel));
		addSauceButton.addActionListener(new IncrementIngredient("Sauce", sauceQuantityLabel));

		/*
		 * Action to decrement an ingredient in the menu.
		 */
		removeMeatButton.addActionListener(new DecrementIngredient("Steak", meatQuantityLabel));
		removeMeatButton.addActionListener(new DecrementIngredient("Poulet pane", meatQuantityLabel));
		removeMeatButton.addActionListener(new DecrementIngredient("Fish", meatQuantityLabel));
		removeBreadButton.addActionListener(new DecrementIngredient("Pain", breadQuantityLabel));
		removeCheeseButton.addActionListener(new DecrementIngredient("Cheddar", cheeseQuantityLabel));
		removeTomatoButton.addActionListener(new DecrementIngredient("Tomate", tomatoQuantityLabel));
		removeCornichonButton.addActionListener(new DecrementIngredient("Cornichon", cornichonQuantityLabel));
		removeSaladButton.addActionListener(new DecrementIngredient("Salade", saladQuantityLabel));
		removeOnionButton.addActionListener(new DecrementIngredient("Oignon", onionQuantityLabel));
		removeChipsButton.addActionListener(new DecrementIngredient("Frites moyenne", chipsQuantityLabel));
		removeSauceButton.addActionListener(new DecrementIngredient("Sauce", sauceQuantityLabel));
	}

	/**
	 * Allow to display the menus with the ingredients and allow to modify of number
	 * of ingredients.
	 *
	 */
	private class ShowMenu implements ActionListener {

		private int numberOfMenu;

		public ShowMenu(int numberOfMenu) {
			super();
			this.numberOfMenu = numberOfMenu;
		}

		/**
		 * Change the background color to menu which selected and display the
		 * ingredients which are in the new menu display.
		 * 
		 * @param e button which change the menu of customer
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			currentMenu = this.numberOfMenu;
			ingredientsInMenu = restaurantManager.getMenus().get(currentMenu).getIngredients();

			if (numberOfMenu == 0) {
				changeFirstMenu.setBackground(Color.LIGHT_GRAY);
				changeSecondMenu.setBackground(Color.WHITE);
				changeThirdMenu.setBackground(Color.WHITE);
			}
			if (numberOfMenu == 1) {
				changeFirstMenu.setBackground(Color.WHITE);
				changeSecondMenu.setBackground(Color.LIGHT_GRAY);
				changeThirdMenu.setBackground(Color.WHITE);
			}
			if (numberOfMenu == 2) {
				changeFirstMenu.setBackground(Color.WHITE);
				changeSecondMenu.setBackground(Color.WHITE);
				changeThirdMenu.setBackground(Color.LIGHT_GRAY);
			}

			changeFirstMenu.setOpaque(true);
			changeSecondMenu.setOpaque(true);
			changeThirdMenu.setOpaque(true);

			meatLabel.setText(String.valueOf(ingredientsInMenu.get(0).getName()));
			meatQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(0).getNbByMenu()));
			breadQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(1).getNbByMenu()));
			saladQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(2).getNbByMenu()));
			cornichonQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(3).getNbByMenu()));
			cheeseQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(4).getNbByMenu()));
			onionQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(5).getNbByMenu()));
			tomatoQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(6).getNbByMenu()));
			chipsQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(7).getNbByMenu()));
			sauceQuantityLabel.setText(String.valueOf(ingredientsInMenu.get(8).getNbByMenu()));

			setVisible(true);
		}
	}

	/**
	 * Use action listener when the user want increment an ingredient in his menu.
	 * 
	 */
	private class IncrementIngredient implements ActionListener {

		private String ingredient;
		private JLabel jLabel;

		public IncrementIngredient(String ingredient, JLabel jLabel) {
			super();
			this.ingredient = ingredient;
			this.jLabel = jLabel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			List<Ingredient> ingredients = restaurantManager.getMenus().get(currentMenu).getIngredients();

			for (Ingredient ingredient : ingredients) {
				if (this.ingredient.equals(ingredient.getName())) {
					restaurantManager.getMenus().get(currentMenu).addIngredient(this.ingredient);
					this.jLabel.setText(String.valueOf(ingredient.getNbByMenu()));
				}
			}
		}
	}

	/**
	 * Use action listener when the user wants decrement an ingredient in the menu.
	 *
	 */
	private class DecrementIngredient implements ActionListener {

		private String ingredient;
		private JLabel jLabel;

		public DecrementIngredient(String ingredient, JLabel jLabel) {
			super();
			this.ingredient = ingredient;
			this.jLabel = jLabel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			List<Ingredient> ingredients = restaurantManager.getMenus().get(currentMenu).getIngredients();

			for (Ingredient ingredient : ingredients) {
				if (this.ingredient.equals(ingredient.getName())) {
					restaurantManager.getMenus().get(currentMenu).decrementIngredient(this.ingredient);
					this.jLabel.setText(String.valueOf(ingredient.getNbByMenu()));
				}
			}
		}
	}
}
