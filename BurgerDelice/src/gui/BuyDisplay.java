package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import process.RestaurantManager;

public class BuyDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel basketLabel = new JLabel("Votre panier : ");

	private MyButton buyButton = new MyButton("Payer");

	private JTextPane basketPane = new JTextPane();
	private JScrollPane jScrollPane = new JScrollPane(basketPane);

	private Dimension preferredSize = new Dimension(250, 100);

	private RestaurantManager restaurantManager;
	private OrderDisplay orderDisplay;

	public BuyDisplay(RestaurantManager restaurantManager, OrderDisplay orderDisplay) {
		this.restaurantManager = restaurantManager;
		this.orderDisplay = orderDisplay;

		init();
	}

	private void init() {
		setLayout(new BorderLayout());

		basketLabel.setBackground(Color.WHITE);
		basketLabel.setOpaque(true);

		basketPane.setPreferredSize(preferredSize);

		buyButton.addActionListener(new BuyBasket());

		add(basketLabel, BorderLayout.NORTH);
		add(jScrollPane);
		add(buyButton, BorderLayout.SOUTH);
	}

	public void updateBasket() {
		String message = "";
		for (Map.Entry<String, Integer> mapentry : restaurantManager.getOrder().getBasket().entrySet()) {
			message += mapentry.getKey() + " : " + mapentry.getValue() + "\n";
		}
		basketPane.setText(message);
	}

	private class BuyBasket implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			restaurantManager.getOrder().buyBasket();
			restaurantManager.getOrder().setDelivering(true);
			
			basketPane.setText("");
			orderDisplay.updateOrder();
		}
	}
}