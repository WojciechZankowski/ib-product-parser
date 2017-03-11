package pl.zankowski.ibpp.util;

import pl.zankowski.ibpp.cli.CommandLineProperties;
import pl.zankowski.ibpp.model.IBExchange;
import pl.zankowski.ibpp.model.IBExchanges;
import pl.zankowski.ibpp.model.IBParserParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wojciech Zankowski
 */
public class IBParserParametersConverter {

    public static IBParserParameters convert(CommandLineProperties properties) {
        validateCommandLineProperties(properties);
        resolveExchanges(properties);
        adjustSecType(properties);
        return new IBParserParameters(properties.getExchanges(), properties.getSecType());
    }

    private static void resolveExchanges(CommandLineProperties properties) {
        List<IBExchange[]> exchanges = getExchanges(properties.getSecType());
        if (properties.isAllNA() || properties.isAll()) {
            for (IBExchange exchange : exchanges.get(0)) {
                properties.addExchange(exchange.getCode());
            }
        }
        if (properties.isAllEU() || properties.isAll()) {
            for (IBExchange exchange : exchanges.get(1)) {
                properties.addExchange(exchange.getCode());
            }
        }
        if (properties.isAllAsia() || properties.isAll()) {
            for (IBExchange exchange : exchanges.get(2)) {
                properties.addExchange(exchange.getCode());
            }
        }
    }

    private static List<IBExchange[]> getExchanges(String secType) {
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
            default:
                throw new IllegalArgumentException("Illegal security type value. Security type " +
                        "cannot has " + secType + " value.");
        }
        return exchanges;
    }

    private static void adjustSecType(CommandLineProperties properties) {
        if (isFutureOptionSecType(properties.getSecType())) {
            properties.setSecType("OPTGRP");
        }
    }

    private static boolean isFutureOptionSecType(String value) {
        return value.equals("FOPTGRP");
    }

    private static void validateCommandLineProperties(CommandLineProperties properties) {
        if (properties.getSecType().isEmpty()) {
            throw new IllegalArgumentException("Illegal security type value. Security type cannot be empty.");
        }
    }

}
