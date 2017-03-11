package pl.zankowski.ibpp.data;

/**
 * @author Wojciech Zankowski
 */
public class IBExchanges {

    public static final IBExchange[] ALL_STOCK_NA = {IBExchange.ARCAEDGE, IBExchange.BATS_BYX,
            IBExchange.BATS_GLOBAL_MARKETS, IBExchange.CHICAGO_EXCHANGE, IBExchange.DIRECT_EDGE,
            IBExchange.DIRECT_EDGEA, IBExchange.IB_VWAP, IBExchange.IEX, IBExchange.INET,
            IBExchange.KNIGHT_SECURITIES, IBExchange.NASDAQ, IBExchange.NASDAQ_OMX_BX, IBExchange
            .NASDAQ_OMX, IBExchange.NYSE, IBExchange.NYSE_AMEX, IBExchange.NYSE_ARCA, IBExchange
            .PINK_OTC, IBExchange.ALPHA_ATS, IBExchange.CHI_X_CANADA, IBExchange.OMEGA_ATS,
            IBExchange.PURE_TRADING, IBExchange.TORONTO_STOCK, IBExchange.TSX_VENTURE, IBExchange
            .MEXICAN_STOCK};

    public static final IBExchange[] ALL_STOCK_EU = {IBExchange.VIENNA_STOCK, IBExchange
            .BATS_EUROPE_BE, IBExchange.CHI_X_EUROPE_BE, IBExchange.ENEXT_BE, IBExchange
            .TURQUOISE_BE, IBExchange.BATS_EUROPE_FR, IBExchange.CHI_X_EUROPE_FR, IBExchange
            .EURONEXT_FRANCE, IBExchange.TURQUOISE_FR, IBExchange.BATS_EUROPE_DE, IBExchange
            .CHI_X_EUROPE_DE, IBExchange.FRANKFURT_STOCK, IBExchange.STUTTGART_STOCK, IBExchange
            .TRADEGATE, IBExchange.TURQUOISE_DE, IBExchange.XETRA, IBExchange.BORSA, IBExchange
            .BATS_EUROPE_NL, IBExchange.CHI_X_EUROPE_NL, IBExchange.EURONEXT_STOCKS_NL, IBExchange
            .TURQUOISE_NL, IBExchange.OMXNO, IBExchange.BVL, IBExchange.BATS_EUROPE_ES, IBExchange
            .BOLSA_MARDID, IBExchange.CHI_X_EUROPE_ES, IBExchange.SWEDISH_STOCK, IBExchange
            .BATS_EUROPE_CH, IBExchange.CHI_X_EUROPE_CH, IBExchange.SWISS_EXCHANGE, IBExchange
            .TURQUOISE_CH, IBExchange.VIRT_X, IBExchange.BATS_EUROPE_UK, IBExchange
            .CHI_X_EUROPE_UK, IBExchange.LONDON_STOCK, IBExchange.LSE_INTERNATIONAL};

    public static final IBExchange[] ALL_STOCK_ASIA = {IBExchange.AUSTRALIAN_STOCK, IBExchange
            .CHI_X_AUSTRALIA, IBExchange.HONG_KONG_STOCKS, IBExchange.SHANGHAI_HONG_KONG,
            IBExchange.NATIONAL_EXCHANGE_INDIA, IBExchange.CHI_X_JAPAN, IBExchange.JAPANNEXT,
            IBExchange.TOKYO_STOCK, IBExchange.SINGAPORE};

    public static final IBExchange[] ALL_OPTIONS_NA = {IBExchange.BATS_GLOBAL_MARKETS, IBExchange
            .BOSTON_OPTIONS, IBExchange.CBOE_C2, IBExchange.CBOE, IBExchange.EDGX_OPTIONS,
            IBExchange.GEMINI, IBExchange.ISE_OPTIONS, IBExchange.MIAX_OPTIONS, IBExchange
            .NASDAQ_OMX, IBExchange.NASDAQ_OMX_BX_OPTIONS, IBExchange.NYSE_AMEX, IBExchange.NYSE_ARCA_OPTIONS,
            IBExchange.PHLX, IBExchange.MONTREAL};

    public static final IBExchange[] ALL_OPTIONS_EU = {IBExchange.EURONEXT_BRUSSELS, IBExchange
            .EURONEXT_FRANCE_OPTIONS, IBExchange.EUREX_DE, IBExchange.BORSA_OPTIONS, IBExchange
            .EURONEXT_NL, IBExchange.TOM, IBExchange.EDXNO, IBExchange.SPANISH_FUTURES, IBExchange
            .NASDAQ_OMX_SE, IBExchange.EUREX_CH, IBExchange.INTERCONTINENTAL};

    public static final IBExchange[] ALL_OPTIONS_ASIA = {IBExchange.AUSTRALIAN_STOCK, IBExchange
            .HONG_KONG_FUTURES, IBExchange.HONG_KONG_STOCKS, IBExchange.NATIONAL_EXCHANGE_INDIA,
            IBExchange.OSAKA, IBExchange.SINGAPORE, IBExchange.KOREA_EXCHANGE};

    public static final IBExchange[] ALL_FUTURES_NA = {IBExchange.CBOE_FUTURES, IBExchange.CBOT,
            IBExchange.CME, IBExchange.ICE_FUTURES, IBExchange.ICE_FUTURES_US, IBExchange
            .NEW_YORK_MERCANTILE, IBExchange.NYSE_LIFFE_US, IBExchange.ONECHICAGO, IBExchange
            .MONTREAL, IBExchange.MEXICAN_DERIVATIVES};

    public static final IBExchange[] ALL_FUTURES_EU = {IBExchange.EURONEXT_BRUSSELS, IBExchange
            .EURONEXT_FRANCE_OPTIONS, IBExchange.EURONEXT_FRANCE, IBExchange.EUREX_DE, IBExchange
            .BORSA_OPTIONS, IBExchange.EURONEXT_NL, IBExchange.TOM, IBExchange.EDXNO, IBExchange
            .SPANISH_FUTURES, IBExchange.NASDAQ_OMX_SE, IBExchange.EUREX_CH, IBExchange
            .INTERCONTINENTAL, IBExchange.INTERCONTINENTAL_SOFT, IBExchange
            .INTERCONTINENTAL_ENERGY};

    public static final IBExchange[] ALL_FUTURES_ASIA = {IBExchange.ASX24, IBExchange
            .HONG_KONG_FUTURES, IBExchange.NATIONAL_EXCHANGE_INDIA, IBExchange.OSAKA, IBExchange
            .SINGAPORE, IBExchange.KOREA_EXCHANGE};

    public static final IBExchange[] ALL_FOP_NA = {IBExchange.CBOT, IBExchange.CME, IBExchange
            .ICE_FUTURES, IBExchange.NEW_YORK_MERCANTILE, IBExchange.NYSE_LIFFE_US, IBExchange
            .MEXICAN_DERIVATIVES};

    public static final IBExchange[] ALL_FOP_EU = {IBExchange.EUREX_DE, IBExchange
            .INTERCONTINENTAL};

    public static final IBExchange[] ALL_FOP_ASIA = {IBExchange.ASX24, IBExchange.OSAKA,
            IBExchange.SINGAPORE};

    public static final IBExchange[] ALL_ETF_NA = {IBExchange.CHICAGO_EXCHANGE, IBExchange
            .NASDAQ_OMX_BX, IBExchange.NYSE, IBExchange.NYSE_AMEX, IBExchange.NYSE_ARCA,
            IBExchange.CHI_X_CANADA, IBExchange.OMEGA_ATS, IBExchange.PURE_TRADING, IBExchange
            .TORONTO_STOCK, IBExchange.MEXICAN_STOCK};

    public static final IBExchange[] ALL_ETF_EU = {IBExchange.EURONEXT_FRANCE, IBExchange
            .CHI_X_EUROPE_DE, IBExchange.FRANKFURT_STOCK, IBExchange.STUTTGART_STOCK, IBExchange
            .XETRA, IBExchange.CHI_X_EUROPE_NL, IBExchange.EURONEXT_NL, IBExchange.BOLSA_MARDID,
            IBExchange.SWEDISH_STOCK, IBExchange.SWISS_EXCHANGE, IBExchange.CHI_X_EUROPE_UK,
            IBExchange.LONDON_STOCK};

    public static final IBExchange[] ALL_ETF_ASIA = {IBExchange.AUSTRALIAN_STOCK, IBExchange
            .HONG_KONG_STOCKS, IBExchange.NATIONAL_EXCHANGE_INDIA};

    public static final IBExchange[] ALL_WARRANTS_NA = {IBExchange.ARCAEDGE, IBExchange.BATS_BYX,
            IBExchange.BATS_GLOBAL_MARKETS, IBExchange.CHICAGO_EXCHANGE, IBExchange.DIRECT_EDGEA,
            IBExchange.INET, IBExchange.ISE_OPTIONS, IBExchange.KNIGHT_SECURITIES, IBExchange
            .NASDAQ_OMX_PSX, IBExchange.NYSE, IBExchange.NYSE_AMEX, IBExchange.NYSE_ARCA,
            IBExchange.PINK_OTC, IBExchange.TSX_VENTURE};

    public static final IBExchange[] ALL_WARRANTS_EU = {IBExchange.FRANKFURT_STOCK, IBExchange
            .STUTTGART_STOCK, IBExchange.TRADELINK};

    public static final IBExchange[] ALL_WARRANTS_ASIA = {IBExchange.AUSTRALIAN_STOCK, IBExchange
            .HONG_KONG_STOCKS};

    public static final IBExchange[] ALL_SP_EU = {IBExchange.EURONEXT_FRANCE, IBExchange
            .FRANKFURT_STOCK, IBExchange.STUTTGART_STOCK, IBExchange.TRADELINK, IBExchange
            .EURONEXT_STOCKS_NL};

    public static final IBExchange[] ALL_SP_ASIA = {IBExchange.HONG_KONG_STOCKS};

    public static final IBExchange[] ALL_SSF_NA = {IBExchange.ONECHICAGO, IBExchange
            .MEXICAN_DERIVATIVES};

    public static final IBExchange[] ALL_SSF_EU = {IBExchange.EUREX_DE, IBExchange.BORSA_OPTIONS,
            IBExchange.EDXNO, IBExchange.SPANISH_FUTURES, IBExchange.EUREX_CH, IBExchange
            .INTERCONTINENTAL};

    public static final IBExchange[] ALL_SSF_ASIA = {IBExchange.HONG_KONG_FUTURES, IBExchange
            .NATIONAL_EXCHANGE_INDIA, IBExchange.KOREA_EXCHANGE};

    public static final IBExchange[] ALL_FOREX = {IBExchange.IDEAL_FX};

    public static final IBExchange[] ALL_METALS = {IBExchange.IDEALPRO};

    public static final IBExchange[] ALL_INDICES_NA = {IBExchange.CBOE_FUTURES, IBExchange.CBOT,
            IBExchange.CME, IBExchange.ISE_OPTIONS, IBExchange.NASDAQ, IBExchange
            .NEW_YORK_MERCANTILE, IBExchange.NYSE, IBExchange.NYSE_AMEX, IBExchange.NYSE_ARCA,
            IBExchange.NYSE_LIFFE_US, IBExchange.ONECHICAGO, IBExchange.PHLX, IBExchange.MONTREAL,
            IBExchange.TORONTO_STOCK};

    public static final IBExchange[] ALL_INDICES_EU = {IBExchange.VIENNA_STOCK, IBExchange
            .EURONEXT_BRUSSELS, IBExchange.EURONEXT_FRANCE, IBExchange.EURONEXT_FRANCE_OPTIONS,
            IBExchange.EUREX_DE, IBExchange.XETRA, IBExchange.BORSA, IBExchange.EURONEXT_NL,
            IBExchange.BOLSA_MARDID, IBExchange.SPANISH_FUTURES, IBExchange.NASDAQ_OMX_SE,
            IBExchange.SWEDISH_STOCK, IBExchange.EUREX_CH, IBExchange.SWISS_EXCHANGE, IBExchange
            .VIRT_X, IBExchange.INTERCONTINENTAL, IBExchange.INTERCONTINENTAL_SOFT, IBExchange
            .LONDON_STOCK};

    public static final IBExchange[] ALL_INDICES_ASIA = {IBExchange.ASX24, IBExchange
            .AUSTRALIAN_STOCK, IBExchange.HONG_KONG_FUTURES, IBExchange.NATIONAL_EXCHANGE_INDIA,
            IBExchange.OSAKA, IBExchange.TOKYO_STOCK, IBExchange.SINGAPORE, IBExchange
            .KOREA_EXCHANGE};

    public static final IBExchange[] ALL_BONDS_NA = {IBExchange.BONDDESK, IBExchange.BONDLARGE,
            IBExchange.KNIGHT_BONDPOINT, IBExchange.KNIGHT_BONDPOINT_MUNIS, IBExchange
            .KNIGHT_BONDPOINT_US, IBExchange.MUNICENTER, IBExchange.NYSE_ARCA_BONDS, IBExchange
            .TRADEWEB, IBExchange.TRADEWEB_MUNIX, IBExchange.TRADEWEB_US};

    public static final IBExchange[] ALL_BONDS_EU = {IBExchange.EURONEXT_BONDS};

    public static final IBExchange[] ALL_BONDS_ASIA = {IBExchange.HONG_KONG_STOCKS};

}
