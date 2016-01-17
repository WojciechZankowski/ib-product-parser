package pl.zankowski.ibpp.data;

/**
 * @author Wojciech Zankowski
 */
public enum IBExchange {

    // US
    ARCAEDGE("arcaedge"),
    BATS_BYX("byx"),
    BATS_GLOBAL_MARKETS("bats"),
    BONDDESK("bonddesk"),
    BONDLARGE("bondlarge"),
    BOSTON_OPTIONS("box"),
    CBOE("cboe"),
    CBOE_C2("cboe2"),
    CBOE_FUTURES("cfe"),
    CBOT("ecbot"),
    CHICAGO_EXCHANGE("chx"),
    CME("globex"),
    DIRECT_EDGE("drctedge"),
    DIRECT_EDGEA("edgea"),
    EDGX_OPTIONS("edgx"),
    GEMINI("gemini"),
    IB_VWAP("vwap"),
    ICE_FUTURES("nybot"),
    ICE_FUTURES_US("iceus"),
    IEX("iex"),
    INET("island"),
    ISE_OPTIONS("iseopt"),
    KNIGHT_BONDPOINT("valubond"),
    KNIGHT_BONDPOINT_MUNIS("valubondm"),
    KNIGHT_BONDPOINT_US("valubondg"),
    KNIGHT_SECURITIES("nite"),
    MIAX_OPTIONS("miax"),
    MUNICENTER("municentm"),
    NASDAQ("nasdaq"),
    NASDAQ_OMX("nasdaqom"),
    NASDAQ_OMX_BX("bex"),
    NASDAQ_OMX_BX_OPTIONS("nasdaqbx"),
    NASDAQ_OMX_PSX("psx"),
    NEW_YORK_MERCANTILE("nymex"),
    NYSE("nyse"),
    NYSE_AMEX("amex"),
    NYSE_ARCA("arca"),
    NYSE_ARCA_OPTIONS("pse"),
    NYSE_ARCA_BONDS("nyse_bonds"),
    NYSE_LIFFE_US("nyseliffe"),
    ONECHICAGO("one"),
    PHLX("phlx"),
    PINK_OTC("pink"),
    TRADEWEB("tradeweb"),
    TRADEWEB_MUNIX("tradewebm"),
    TRADEWEB_US("tradewebg"),

    // CANADA
    ALPHA_ATS("alpha"),
    CHI_X_CANADA("chix_ca"),
    MONTREAL("cde"),
    OMEGA_ATS("omega"),
    PURE_TRADING("pure"),
    TORONTO_STOCK("tse"),
    TSX_VENTURE("venture"),

    // MEXICO
    MEXICAN_DERIVATIVES("mexder"),
    MEXICAN_STOCK("mexi"),

    // AUSTRIA
    VIENNA_STOCK("vse"),

    // BELGIUM
    BATS_EUROPE_BE("bateen-be"),
    CHI_X_EUROPE_BE("chixen-be"),
    ENEXT_BE("enext.be"),
    EURONEXT_BRUSSELS("belfox"),
    TURQUOISE_BE("trqx-be"),

    // EUROPEAN UNION
    EURONEXT_BONDS("euronext"),

    // FRANCE
    BATS_EUROPE_FR("bateen-fr"),
    CHI_X_EUROPE_FR("chixen-fr"),
    EURONEXT_FRANCE("matif"),
    EURONEXT_FRANCE_OPTIONS("monep"),
    EURONEXT_FRANCE_ETF("sbf"),
    TURQUOISE_FR("trqx-fr"),

    // GERMANY
    BATS_EUROPE_DE("batede"),
    CHI_X_EUROPE_DE("chixde"),
    EUREX_DE("dtb"),
    FRANKFURT_STOCK("fwb"),
    STUTTGART_STOCK("swb"),
    TRADEGATE("tgate"),
    TRADELINK("tlink"),
    TURQUOISE_DE("trqxde"),
    XETRA("ibis"),

    // ITALY
    BORSA("bvme"),
    BORSA_OPTIONS("idem"),

    // NETHERLANDS
    BATS_EUROPE_NL("beteen"),
    CHI_X_EUROPE_NL("chixen"),
    EURONEXT_NL("fta"),
    EURONEXT_STOCKS_NL("aeb"),
    TOM("tom"),
    TURQUOISE_NL("trqxen"),

    // NORWAY
    EDXNO("edxno"),
    OMXNO("omxno"),

    // PORTUGAL
    BVL("bvl"),

    // SPAIN
    BATS_EUROPE_ES("batees"),
    BOLSA_MARDID("bm"),
    CHI_X_EUROPE_ES("chixes"),
    SPANISH_FUTURES("meffrv"),

    // SWEDEN
    NASDAQ_OMX_SE("oms"),
    SWEDISH_STOCK("sfb"),

    // SWITZERLAND
    BATS_EUROPE_CH("batech"),
    CHI_X_EUROPE_CH("chixch"),
    EUREX_CH("soffex"),
    SWISS_EXCHANGE("ebs"),
    TURQUOISE_CH("trqxch"),
    VIRT_X("virtx"),

    // UNITED KINGDOM
    BATS_EUROPE_UK("bateuk"),
    CHI_X_EUROPE_UK("chixuk"),
    INTERCONTINENTAL_ENERGY("ipe"),
    INTERCONTINENTAL("iceeu"),
    INTERCONTINENTAL_SOFT("iceeusoft"),
    LONDON_STOCK("lse"),
    LSE_INTERNATIONAL("lseiob1"),

    // AUSTRALIA
    ASX24("snfe"),
    AUSTRALIAN_STOCK("asx"),
    CHI_X_AUSTRALIA("chixau"),

    // HONG KONG
    HONG_KONG_FUTURES("hkfe"),
    HONG_KONG_STOCKS("sehk"),
    SHANGHAI_HONG_KONG("sehkntl"),

    // INDIA
    NATIONAL_EXCHANGE_INDIA("nse"),

    // JAPAN
    CHI_X_JAPAN("chixj"),
    JAPANNEXT("jpnnext"),
    OSAKA("ose.jpn"),
    TOKYO_INDICES("tse.jpn"),
    TOKYO_STOCK("tsej"),

    // SINGAPORE
    SINGAPORE("sgx"),

    // SOUTH KOREA
    KOREA_EXCHANGE("kse"),

    // GLOBAL
    IDEAL_FX("ibfxpro"),
    IDEALPRO("idealpro_metals"),

    UNKNOWN("");

    private final String code;

    IBExchange(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static IBExchange getExchangeFromCode(String code) {
        for (IBExchange exchange : values()) {
            if (exchange.getCode().equals(code)) {
                return exchange;
            }
        }
        return IBExchange.UNKNOWN;
    }

}
