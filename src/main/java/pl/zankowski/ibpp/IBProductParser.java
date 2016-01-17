package pl.zankowski.ibpp;

import pl.zankowski.ibpp.data.IBExchange;
import pl.zankowski.ibpp.data.IBExchanges;
import pl.zankowski.ibpp.io.FileWriter;
import pl.zankowski.ibpp.io.HTMLParser;
import pl.zankowski.ibpp.io.parser.SimpleParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wojciech Zankowski
 */
public class IBProductParser {

	private HTMLParser htmlParser;
	private FileWriter fileWriter;

	public static void main(String[] args) {
		IBProductParser ibpp = new IBProductParser();
		ibpp.start(args);
	}

	private void start(String[] args) {
		CommandLineProperties properties = new CommandLineProperties();

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-e")) {
				while (++ i < args.length) {
					if (args[i].startsWith("-")) {
						break;
					}
					if (args[i].equals("all")) {
						properties.setAll(true);
					} else if (args[i].equals("allna")) {
						properties.setAllna(true);
					} else if (args[i].equals("alleu")) {
						properties.setAlleu(true);
					} else if (args[i].equals("allasia")) {
						properties.setAllasia(true);
					} else {
						properties.addExchange(args[i]);
					}
				}
			}
			if (args[i].equals("-t")) {
				if (++ i < args.length) {
					properties.setSecType(args[i]);
				}
			}
		}

		setUpProperties(properties);
		run(properties);
	}

	private void setUpProperties(CommandLineProperties properties) {
		if (properties.getSecType().isEmpty()) {
			throw new IllegalArgumentException("Illegal security type value. Security type cannot " +
					"" + "be empty.");
		}

		setUpExchanges(properties);

		if (properties.getExchanges().isEmpty()) {
			throw new IllegalArgumentException("Exchange list cannot be empty.");
		}
	}

	private void setUpExchanges(CommandLineProperties properties) {
		List<IBExchange[]> exchanges = getExchanges(properties.getSecType());
		if (properties.isAllna() || properties.isAll()) {
			for (IBExchange exchange : exchanges.get(0)) {
				properties.addExchange(exchange.getCode());
			}
		}
		if (properties.isAlleu() || properties.isAll()) {
			for (IBExchange exchange : exchanges.get(1)) {
				properties.addExchange(exchange.getCode());
			}
		}
		if (properties.isAllasia() || properties.isAll()) {
			for (IBExchange exchange : exchanges.get(2)) {
				properties.addExchange(exchange.getCode());
			}
		}
	}

	private List<IBExchange[]> getExchanges(String secType) {
		List<IBExchange[]> exchanges = new ArrayList<>(3);
		switch (secType) {
			case "STK":
				exchanges.add(IBExchanges.ALL_STOCK_NA);
				exchanges.add(IBExchanges.ALL_STOCK_EU);
				exchanges.add(IBExchanges.ALL_STOCK_ASIA);
				break;
			case "OPTGRP":
				exchanges.add(IBExchanges.ALL_OPTIONS_NA);
				exchanges.add(IBExchanges.ALL_OPTIONS_EU);
				exchanges.add(IBExchanges.ALL_OPTIONS_ASIA);
				break;
			case "FUTGRP":
				exchanges.add(IBExchanges.ALL_FUTURES_NA);
				exchanges.add(IBExchanges.ALL_FUTURES_EU);
				exchanges.add(IBExchanges.ALL_FUTURES_ASIA);
				break;
			case "FOPTGRP":
				exchanges.add(IBExchanges.ALL_FOP_NA);
				exchanges.add(IBExchanges.ALL_FOP_EU);
				exchanges.add(IBExchanges.ALL_FOP_ASIA);
				break;
			case "ETFS":
				exchanges.add(IBExchanges.ALL_ETF_NA);
				exchanges.add(IBExchanges.ALL_ETF_EU);
				exchanges.add(IBExchanges.ALL_ETF_ASIA);
				break;
			case "WAR":
				exchanges.add(IBExchanges.ALL_WARRANTS_NA);
				exchanges.add(IBExchanges.ALL_WARRANTS_EU);
				exchanges.add(IBExchanges.ALL_WARRANTS_ASIA);
				break;
			case "IOPT":
				exchanges.add(new IBExchange[]{});
				exchanges.add(IBExchanges.ALL_SP_EU);
				exchanges.add(IBExchanges.ALL_SP_ASIA);
				break;
			case "SSF":
				exchanges.add(IBExchanges.ALL_SSF_NA);
				exchanges.add(IBExchanges.ALL_SSF_EU);
				exchanges.add(IBExchanges.ALL_SSF_ASIA);
				break;
			case "FX":
				exchanges.add(IBExchanges.ALL_FOREX);
				exchanges.add(new IBExchange[]{});
				exchanges.add(new IBExchange[]{});
				break;
			case "CMDTY":
				exchanges.add(IBExchanges.ALL_METALS);
				exchanges.add(new IBExchange[]{});
				exchanges.add(new IBExchange[]{});
				break;
			case "IND":
				exchanges.add(IBExchanges.ALL_INDICES_NA);
				exchanges.add(IBExchanges.ALL_INDICES_EU);
				exchanges.add(IBExchanges.ALL_INDICES_ASIA);
				break;
			case "BOND":
				exchanges.add(IBExchanges.ALL_BONDS_NA);
				exchanges.add(IBExchanges.ALL_BONDS_EU);
				exchanges.add(IBExchanges.ALL_BONDS_ASIA);
				break;
			default:
				throw new IllegalArgumentException("Illegal security type value. Security type " +
						"cannot has " + secType + " value.");
		}
		return exchanges;
	}

	private void run(CommandLineProperties properties) {
		htmlParser = new HTMLParser();
		fileWriter = new FileWriter(new SimpleParser());

		for (String exchange : properties.getExchanges()) {
			try {
				fileWriter.write(htmlParser.parseProducts(exchange, properties.getSecType()),
						exchange, properties.getSecType());
			} catch (Exception e) {
				// cry
				e.printStackTrace();
			}
		}
	}

	public class CommandLineProperties {

		private final List<String> exchanges = new ArrayList<>();
		private String secType;
		private boolean all;
		private boolean alleu;
		private boolean allna;
		private boolean allasia;

		public void addExchange(String exchange) {
			exchanges.add(exchange);
		}

		public List<String> getExchanges() {
			return exchanges;
		}

		public String getSecType() {
			return secType;
		}

		public void setSecType(String secType) {
			this.secType = secType;
		}

		public boolean isAll() {
			return all;
		}

		public void setAll(boolean all) {
			this.all = all;
		}

		public boolean isAlleu() {
			return alleu;
		}

		public void setAlleu(boolean alleu) {
			this.alleu = alleu;
		}

		public boolean isAllna() {
			return allna;
		}

		public void setAllna(boolean allna) {
			this.allna = allna;
		}

		public boolean isAllasia() {
			return allasia;
		}

		public void setAllasia(boolean allasia) {
			this.allasia = allasia;
		}
	}

}
