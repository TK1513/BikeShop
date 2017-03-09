import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;


@SuppressWarnings("serial")
public class BikeShopGUI extends JFrame {
	private static final double DEPOSIT_RATE = .3;
	private BikePanel bikePanel;
	private AccessoryPanel itemPanel;
	private CostPanel costPanel;
	private TransactionPanel transPanel;
	private TransactionPanel returnPanel;
	private BoxPanel boxPanel;
	private BoxPanel downPanel;
	private RestockPanel restockPanel;
	private static final int PREFERRED_WIDTH = 860;
	private static final int PREFERRED_HEIGHT = 750;
	private static final Dimension PREFERRED_SIZE = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	private ArrayList<Bike> bikeHolder = new ArrayList<Bike>();
	private ArrayList<String> bikeMake = new ArrayList<String>();
	private ArrayList<String> itemHolder = new ArrayList<String>();
	private ArrayList<Bike> bikesToText = new ArrayList<Bike>();
	private ArrayList<String> itemsToText = new ArrayList<String>();

	public BikeShopGUI() {
		super("Bike Shop Calculator");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				setVisible(false);
			}
		});
		int number = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bikeMake.add("Giant");
		bikeMake.add("Marin");
		bikeMake.add("Scott");
		String bikeMan = new String("bikeManagement.csv");
		File bikeFile = new File(bikeMan);
		String itemMan = new String("itemManagement.csv");
		File itemFile = new File(itemMan);
		Scanner bikeReader = null;
		Scanner itemReader = null;
		try {
			bikeReader = new Scanner(new FileInputStream(bikeFile));
			itemReader = new Scanner(new FileInputStream(itemFile));
			int j = 0;
			while (j != 3) {
				bikeReader.nextLine();
				itemReader.nextLine();
				j++;
			}
			while (bikeReader.hasNext() != false) {

				String input = bikeReader.nextLine();
				String[] holder = input.split(",");

				String bikeNumber = holder[0];
				String make = holder[1];
				String model = holder[2];

				if (model.equals("Road")) {
					bikeHolder.add(new Racer(bikeNumber, make, model));
				} else if (model.equals("Off-Road")) {
					bikeHolder.add(new Mountain(bikeNumber, make, model));
				} else {
					bikeHolder.add(new Tandem(bikeNumber, make, model));
				}
			}
			while (itemReader.hasNext() != false) {
				String input = itemReader.nextLine();
				itemHolder.add(input);
			}
			itemReader.close();
			bikeReader.close();
		} catch (IOException e) {
		}

		Random rand = new Random();
		if (bikeHolder.isEmpty()) {
			for(int x =0; x < 20; x++){
				int numForMake = rand.nextInt(bikeMake.size()-1);
				bikeHolder.add(new Racer(Integer.toString(number), bikeMake.get(numForMake), "Road"));
				number++;
				bikeHolder.add(new Tandem(Integer.toString(number), bikeMake.get(numForMake), "Tandem"));
				number++;
				bikeHolder.add(new Mountain(Integer.toString(number), bikeMake.get(numForMake), "Off-Road"));
				number++;
			}
		}

		if (itemHolder.isEmpty()) {
			for(int x = 0; x < 50; x++){
				itemHolder.add("Helmet");
				itemHolder.add("Training Wheels");
				itemHolder.add("Gloves");
				itemHolder.add("Knee Pads");
				itemHolder.add("Basket");
			}
		}

		final Container contentPane = getContentPane();
		JTabbedPane tabbedArea = new JTabbedPane();
		tabbedArea.add("Store Page", setupMain());
		tabbedArea.add("Returns", setupCustomerList());
		tabbedArea.add("Restock Shop", setupBuyPage());
		contentPane.add(tabbedArea);
		setSize(PREFERRED_SIZE);

	}

	private Container setupBuyPage() {
		restockPanel = new RestockPanel();
		boxPanel = new BoxPanel();
		boxPanel.setBorder(BorderFactory.createTitledBorder("Reciept"));
		restockPanel.getRestockButton().addActionListener(new ReceiptListener());

		JPanel buyPage = new JPanel();
		buyPage.setLayout(new GridLayout(2, 1));
		buyPage.add(restockPanel);
		buyPage.add(boxPanel);

		setSize(PREFERRED_SIZE);
		setResizable(false);
		return buyPage;
	}

	public void showWindow() {
		setVisible(true);
	}

	private Container setupCustomerList() {
		returnPanel = new TransactionPanel();
		downPanel = new BoxPanel();

		returnPanel.setBorder(BorderFactory.createTitledBorder("Return"));
		returnPanel.getTransactionAdder().setText("Retrieve Rental Information");
		returnPanel.getTransactionAdder().addActionListener(new RetrieveButtonListener());

		downPanel.setBorder(BorderFactory.createTitledBorder("Customer's Information"));

		JPanel customerReturn = new JPanel();
		customerReturn.setLayout(new GridLayout(2, 1));
		customerReturn.add(returnPanel);
		customerReturn.add(downPanel);
		setSize(PREFERRED_SIZE);
		setResizable(false);
		return customerReturn;
	}

	private Container setupMain() {
		bikePanel = new BikePanel();
		itemPanel = new AccessoryPanel();
		costPanel = new CostPanel();
		transPanel = new TransactionPanel();

		JPanel mainStore = new JPanel();
		mainStore.setLayout(new BorderLayout());
		mainStore.add(bikePanel, BorderLayout.WEST);
		mainStore.add(itemPanel, BorderLayout.EAST);
		mainStore.add(costPanel, BorderLayout.SOUTH);
		mainStore.add(transPanel, BorderLayout.NORTH);

		costPanel.getCalcButton().addActionListener(new CalcButtonListener());
		costPanel.getExitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// write the bikes and items currently in the arraylists to the
				// bikemanagement and item management .csv files
				String itemOutput = null;
				String bikeOutput = null;
				try {
					File bikeFile = new File("bikeManagement.csv");
					File itemFile = new File("itemManagement.csv");

					bikeFile.delete();
					itemFile.delete();

					if (!itemFile.exists()) {
						itemFile.createNewFile();
					}
					if (!bikeFile.exists()) {
						bikeFile.createNewFile();
					}

					FileOutputStream itemWriter = new FileOutputStream(itemFile);
					FileOutputStream bikeWriter = new FileOutputStream(bikeFile);

					StringBuilder bikeBuild = new StringBuilder();
					StringBuilder itemBuild = new StringBuilder();
					String bikeStart = new String("Bike Inventory Records,,,,,," + "\n" + ",,,,,,," 
							+ "\n" + "BikeNumber,BikeMake,BikeModel" + "\n");
					String itemStart = new String("Item Inventory Records,,,,,," + "\n" + ",,,,,,,"
							+ "\n" + "ItemName" + "\n");

					byte[] itemStarter = itemStart.getBytes();
					byte[] bikeStarter = bikeStart.getBytes();
					bikeWriter.write(bikeStarter);
					bikeWriter.flush();
					itemWriter.write(itemStarter);
					itemWriter.flush();

					for(Bike bike : bikeHolder){
						bikeBuild.append(bike.getBikeNumber()).append(",")
						.append(bike.getMake()).append(",")
						.append(bike.getModel()).append("\n");

						bikeOutput = bikeBuild.toString();	
					}	
					byte[] bikeBytes = bikeOutput.getBytes();
					bikeWriter.write(bikeBytes);

					bikeWriter.flush();
					bikeWriter.close();

					for (String item : itemHolder) {
						String temp = item + "\n";
						if (itemOutput != null) {
							itemBuild.append(itemOutput);
						}
						itemBuild.append(temp);
						itemOutput = itemBuild.toString();
						itemBuild.setLength(0);
					}

					byte[] itemBytes = itemOutput.getBytes();
					itemWriter.write(itemBytes);

					itemWriter.flush();
					itemWriter.close();	
				} catch (IOException ioe) {}

				// Exit the application.
				System.exit(0);
			}
		});
		transPanel.getTransactionAdder().addActionListener(new AdderButtonListener());
		setSize(PREFERRED_SIZE);
		setResizable(false);
		return mainStore;
	}

	public ArrayList<Bike> getBikeHolder() {
		return bikeHolder;
	}

	public void setBikeHolder(ArrayList<Bike> bikeHolder) {
		this.bikeHolder = bikeHolder;
	}

	private class ReceiptListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			StringBuilder longMessage = null;
			DecimalFormat dollar = new DecimalFormat("00.00");
			String newLine = "\n";

			restockPanel.inventoryAdd();
			double total = restockPanel.getBuyCost();
			longMessage = new StringBuilder("").append(newLine);

			int buyHelmet = Integer.parseUnsignedInt(restockPanel.getFieldHelmet().getText());
			int buyKneePads = Integer.parseUnsignedInt(restockPanel.getFieldPads().getText());
			int buyTrainingWheels = Integer.parseUnsignedInt(restockPanel.getFieldWheels().getText());
			int buyGloves = Integer.parseUnsignedInt(restockPanel.getFieldGloves().getText());
			int buyBasket = Integer.parseUnsignedInt(restockPanel.getFieldBasket().getText());
			int racer = Integer.parseUnsignedInt(restockPanel.getFieldRacer().getText());
			int mountain = Integer.parseUnsignedInt(restockPanel.getFieldMountain().getText());
			int tandem = Integer.parseUnsignedInt(restockPanel.getFieldTandem().getText());

			if (total != 0) {
				while (buyHelmet != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Helmet ").append("\t\t\t  $12.00")
							.append(newLine);
					buyHelmet -= 1;
				}
				while (buyKneePads != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Knee Pads").append("\t\t\t  $08.00")
							.append(newLine);
					buyKneePads -= 1;
				}
				while (buyTrainingWheels != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Training Wheels").append("\t\t  $10.00")
							.append(newLine);
					buyTrainingWheels -= 1;
				}
				while (buyGloves != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Gloves").append("\t\t\t  $05.00")
							.append(newLine);
					buyGloves -= 1;
				}
				while (buyBasket != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Basket").append("\t\t\t  $10.00")
							.append(newLine);
					buyBasket -= 1;
				}
				while (racer != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Race-Style").append("\t\t\t  $15.00")
							.append(newLine);
					racer -= 1;
				}
				while (mountain != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Mountain").append("\t\t\t  $15.00")
							.append(newLine);
					mountain -= 1;
				}
				while (tandem != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Tandem").append("\t\t\t  $15.00")
							.append(newLine);
					tandem -= 1;
				}

				longMessage = new StringBuilder(longMessage).append(newLine).append(newLine).append(newLine);
				longMessage = new StringBuilder(longMessage).append("Total Cost:").append("\t\t\t $")
						.append(dollar.format(total)).append(newLine);
				boxPanel.getResultList().setText(longMessage.toString());
			}

			restockPanel.getFieldHelmet().setText("0");
			restockPanel.getFieldPads().setText("0");
			restockPanel.getFieldGloves().setText("0");
			restockPanel.getFieldWheels().setText("0");
			restockPanel.getFieldBasket().setText("0");
			restockPanel.getFieldRacer().setText("0");
			restockPanel.getFieldMountain().setText("0");
			restockPanel.getFieldTandem().setText("0");
		}
	}

	private class RetrieveButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String bikeInfo = null, itemInfo = null, rentLength = null, name = null, age = null, phoneNumber = null,
					address = null, gender = null, date = null, cost = null, returnDate = null;
			StringBuilder info = new StringBuilder();
			name = returnPanel.getNameField().getText();
			age = returnPanel.getAgeField().getText();
			phoneNumber = returnPanel.getPhoneField().getText();
			address = returnPanel.getAddressField().getText();

			if (returnPanel.getMale().isSelected()) {
				gender = "M";
			} else {
				gender = "F";
			}

			File transFile = new File("Transaction.txt");
			String input = null;
			LineNumberReader lineNum = null;
			Scanner reader = null;

			try {
				int number = 0;
				reader = new Scanner(new FileInputStream(transFile));
				lineNum = new LineNumberReader(new FileReader(transFile));
				int j = 0;
				while (j != 1) {
					reader.nextLine();
					j++;
					number++;
				}
				while (reader.hasNext() != false) {
					input = reader.nextLine();
					if (input.contains(name) && input.contains(address) && input.contains(phoneNumber)
							&& (input.contains(gender) || input.contains(age))) {
						String[] holder = input.split(",");
						String[] same = Arrays.copyOf(holder, holder.length);
						number += lineNum.getLineNumber();
						StringBuilder a = new StringBuilder(0);
						while ((isItem(same[0])) == true) {
							String temp = null;
							temp = same[0];

							if (itemInfo != null) {
								a.append(itemInfo).append("\n");
							}
							a.append(temp);
							itemInfo = a.toString();
							a.setLength(0);
							if (((isItem(same[0])) == true)) { // i = 8 while holder.length = 5 which causes i > holder.length which makes no sense
								holder = Arrays.copyOfRange(holder, 1, holder.length);
							} else {
								holder = Arrays.copyOfRange(holder, 0, holder.length);
							}
							itemHolder.add(temp);
							same = Arrays.copyOf(holder, holder.length);
						}
						same = Arrays.copyOf(holder, holder.length);

						while (same[2].equals("Road") || same[2].equals("Off-Road") || same[2].equals("Tandem")) {

							String bikeNumber = holder[0];
							String make = holder[1];
							String model = holder[2];

							if (bikeInfo != null) {
								a.append(bikeInfo).append("\n");
							}

							a.append("Bike Serial: ").append(bikeNumber).append("\t").append("Bike Make: ").append(make)
							.append("\t").append("Bike Type: ").append(model);
							bikeInfo = a.toString();
							a.setLength(0);

							if (model.equals("Road")) {
								bikeHolder.add(new Racer(bikeNumber, make, model));
							} else if (model.equals("Off-Road")) {
								bikeHolder.add(new Mountain(bikeNumber, make, model));
							} else if (model.equals("Tandem")) {
								bikeHolder.add(new Tandem(bikeNumber, make, model));
							}
							if (same[2].equals("Road") || same[2].equals("Off-Road") || same[2].equals("Tandem")) {
								holder = Arrays.copyOfRange(holder, 3, holder.length);
								same = Arrays.copyOf(holder, holder.length);
							}
						}
						NumberFormat nf = NumberFormat.getCurrencyInstance();
						cost = holder[5];
						double amount = Double.parseDouble(cost);
						rentLength = holder[6];
						long rentTime = Integer.parseInt(rentLength);
						date = holder[7];

						returnDate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); 

						LocalDate addedDate = LocalDate.parse(date,formatter).plusDays(rentTime);
						date = addedDate.format(formatter);

						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");


						Date returnTime = sdf.parse(date);
						Date dateReturned = sdf.parse(returnDate);


						boolean isLate = false;
						double late = 0.0;

						if(dateReturned.compareTo(returnTime) > 0){
							late = amount * .3;
							amount += late;
							isLate = true;
						}
						String lateFee = nf.format(late);
						cost = nf.format(amount);

						if(isLate == true){
							cost =  lateFee + " (Late fee)" + "\t" + cost + " (Late)";
						}
						RandomAccessFile raf = new RandomAccessFile(transFile, "rw");
						for (int x = 0; x < number; x++) {
							raf.readLine();
						}
						long writePos = raf.getFilePointer();
						raf.readLine();
						long readPos = raf.getFilePointer();

						byte[] bytes = new byte[1024];

						int y;
						while (-1 != (y = raf.read(bytes))) {
							raf.seek(writePos);
							raf.write(bytes, 0, y);
							readPos += y;
							writePos += y;
							raf.seek(readPos);
						}
						raf.setLength(writePos);
						raf.close();
					}
					if(!(itemInfo.equals(null))){
						info.append(itemInfo).append("\n").append("\n");
					}else{
						info.append("No items rented.").append("\n").append("\n");
					}

					if(!(bikeInfo.equals(null)))
					{
						info.append(bikeInfo).append("\n").append("\n");
					}else{
						info.append("No bikes rented.").append("\n").append("\n");
					}

					info.append("Price: ").append(cost).append("\t");
					info.append("Date Returned: ").append(returnDate).append("\t");
					info.append("Rental Period: ").append(rentLength).append("\t");
					info.append("Date Due: ").append(date);
					downPanel.getResultList().setText(info.toString());
				}	
				reader.close();
			} catch (IOException | ParseException exce) {}



		}
	}

	public boolean isItem(String string) {
		if (string.equals("Helmet") || string.equals("Gloves") || string.equals("Knee Pads")
				|| string.equals("Training Wheels") || string.equals("Basket")) {
			return true;
		} else {
			return false;
		}
	}

	private class AdderButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String bikeInfo = null, itemInfo = null, name, age, phoneNumber, address, gender, rentLength;
			double cost = 0.0;
			try{
				if((costPanel.getSubBikeField().getText()).equals(null)||(costPanel.getSubBikeField().getText()).equals("")){
					throw new NoTransactionException();
				}else{
					name = transPanel.getNameField().getText();
					age = transPanel.getAgeField().getText();
					phoneNumber = transPanel.getPhoneField().getText();
					address = transPanel.getAddressField().getText();
					rentLength = bikePanel.getNumLength().getText();

					if (transPanel.getMale().isSelected()) {
						gender = "M";
					} else {
						gender = "F";
					}
					bikesToText = new ArrayList<Bike>(bikePanel.getCurrentBikes());
					itemsToText = new ArrayList<String>(itemPanel.getCurrentItems());
					StringBuilder b = new StringBuilder();
					StringBuilder it = new StringBuilder();
					if (bikesToText.get(0) != null || itemsToText.isEmpty()) {
						for (Bike bike : bikesToText) {
							if (bike.getBikeNumber() != null) {
								String temp = null;
								temp = bike.getBikeNumber() + "," + bike.getMake() + "," + bike.getModel() + ",";
								if (bikeInfo != null) {
									b.append(bikeInfo);
								}
								b.append(temp);
								bikeInfo = b.toString();
								b.setLength(0);
							}
						}
					}
					for (String item : itemsToText) {
						if (item != null) {
							String temp = item + ",";
							if (itemInfo != null) {
								it.append(itemInfo);
							}
							it.append(temp);
							itemInfo = it.toString();
							it.setLength(0);
						}
					}

					String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
					String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
					cost = Double.parseDouble(costPanel.getSubBikeField().getText())
							+ Double.parseDouble(costPanel.getSubItemField().getText())
							- Double.parseDouble(costPanel.getPayField().getText());

					try {
						File fileName = new File("Transaction.txt");
						if (!fileName.exists()) {
							fileName.createNewFile();
						}
						FileOutputStream fileWriter = new FileOutputStream(fileName, true);

						StringBuilder sa = new StringBuilder();
						StringBuilder sb = new StringBuilder();

						String output = name + "," + age + "," + gender + "," + address + "," + phoneNumber + "," + cost + ","
								+ rentLength + "," + date + "," + timeStamp + "\n";
						if (bikeInfo != null) {
							output = sa.append(bikeInfo).append(output).toString();
						}
						if (itemInfo != null) {
							output = sb.append(itemInfo).append(output).toString();
						}

						byte[] bytes = output.getBytes();

						fileWriter.write(bytes);
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e1) {}
				}
			}catch(NoTransactionException nte){}
		}

	}

	private class CalcButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double rentItem;
			double rentBike;
			double deposit;
			double total;
			double buy;
			int length;
			JTextArea reciept = new JTextArea(6, 25);
			StringBuilder longMessage = null;
			JFrame frame = new JFrame();
			DecimalFormat dollar = new DecimalFormat("00.00");
			String newLine = "\n";

			int buyHelmet = Integer.parseUnsignedInt(itemPanel.getFieldBuyHelmet().getText());
			int rentHelmet = Integer.parseUnsignedInt(itemPanel.getFieldHelmet().getText());
			int buyKneePads = Integer.parseUnsignedInt(itemPanel.getFieldBuyPads().getText());
			int rentKneePads = Integer.parseUnsignedInt(itemPanel.getFieldPads().getText());
			int buyTrainingWheels = Integer.parseUnsignedInt(itemPanel.getFieldBuyWheels().getText());
			int rentTrainingWheels = Integer.parseInt(itemPanel.getFieldWheels().getText());
			int buyGloves = Integer.parseUnsignedInt(itemPanel.getFieldBuyGloves().getText());
			int rentGloves = Integer.parseUnsignedInt(itemPanel.getFieldGloves().getText());
			int buyBasket = Integer.parseUnsignedInt(itemPanel.getFieldBuyBasket().getText());
			int rentBasket = Integer.parseUnsignedInt(itemPanel.getFieldBasket().getText());
			int racer = Integer.parseUnsignedInt(bikePanel.getNumRacer().getText());
			int mountain = Integer.parseUnsignedInt(bikePanel.getNumMountain().getText());
			int tandem = Integer.parseUnsignedInt(bikePanel.getNumTandem().getText());

			length = Integer.parseUnsignedInt(bikePanel.getNumLength().getText());
			rentItem = length * itemPanel.getItemCost(itemHolder);
			rentBike = bikePanel.getBikeCost(bikeHolder);
			buy = itemPanel.getBuyCost();
			deposit = (rentBike + rentItem) * DEPOSIT_RATE;
			total = rentBike + rentItem - deposit + buy;

			costPanel.getSubBikeField().setText(dollar.format(rentBike) + "	   ");
			costPanel.getSubItemField().setText(dollar.format(rentItem) + "     ");
			costPanel.getPayField().setText(dollar.format(deposit) + "     ");
			costPanel.getBuyField().setText(dollar.format(buy) + "     ");
			costPanel.getTotalField().setText(dollar.format(total));

			// used to sent a string to the boxPanel to have a scroll box
			longMessage = new StringBuilder("").append(newLine);
			if (total != 0 || deposit != 0) {
				while (buyHelmet != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Helmet ").append("\t\t\t  $08.00")
							.append(newLine);
					buyHelmet -= 1;
				}
				while (rentHelmet != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Helmet").append("\t\t\t  $00.50")
							.append(newLine);
					rentHelmet -= 1;
				}
				while (buyKneePads != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Knee Pads").append("\t\t\t  $06.00")
							.append(newLine);
					buyKneePads -= 1;
				}
				while (rentKneePads != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Knee Pads").append("\t\t  $00.50")
							.append(newLine);
					rentKneePads -= 1;
				}
				while (buyTrainingWheels != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Training Wheels").append("\t\t  $05.00")
							.append(newLine);
					buyTrainingWheels -= 1;
				}
				while (rentTrainingWheels != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Training Wheels").append("\t\t  $00.50")
							.append(newLine);
					rentTrainingWheels -= 1;
				}
				while (buyGloves != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Gloves").append("\t\t\t  $03.00")
							.append(newLine);
					buyGloves -= 1;
				}
				while (rentGloves != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Gloves").append("\t\t\t  $00.50")
							.append(newLine);
					rentGloves -= 1;
				}
				while (buyBasket != 0) {
					longMessage = new StringBuilder(longMessage).append("Buy: Basket").append("\t\t\t  $08.00")
							.append(newLine);
					buyBasket -= 1;
				}
				while (rentBasket != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Basket").append("\t\t\t  $00.50")
							.append(newLine);
					rentBasket -= 1;
				}
				while (racer != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Race-Style").append("\t\t  $04.50")
							.append(newLine);
					racer -= 1;
				}
				while (mountain != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Mountain").append("\t\t\t  $05.50")
							.append(newLine);
					mountain -= 1;
				}
				while (tandem != 0) {
					longMessage = new StringBuilder(longMessage).append("Rent: Tandem").append("\t\t\t  $07.50")
							.append(newLine);
					tandem -= 1;
				}

				longMessage = new StringBuilder(longMessage).append(newLine).append(newLine).append(newLine);
				
				longMessage = new StringBuilder(longMessage).append("Initial Deposit: ").append("\t\t $")
						.append(dollar.format(deposit)).append(newLine);
				
				longMessage = new StringBuilder(longMessage).append("Rental Period (Days):").append("\t\t ")
						.append(length).append(newLine);
				longMessage = new StringBuilder(longMessage).append("Total After:").append("\t\t\t $")
						.append(dollar.format(total)).append(newLine);
			}
			reciept.setText(longMessage.toString());
			JScrollPane scrollPane = new JScrollPane(reciept);
			scrollPane.setPreferredSize(new Dimension(350, 600));
			JOptionPane.showMessageDialog(frame, scrollPane, "Receipt", JOptionPane.INFORMATION_MESSAGE);

		}
	}

}
