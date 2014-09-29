package com.xeiam.xchange.okcoin;

import java.util.Arrays;
import java.util.List;

import com.xeiam.xchange.BaseExchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.okcoin.service.polling.OkCoinAccountService;
import com.xeiam.xchange.okcoin.service.polling.OkCoinMarketDataService;
import com.xeiam.xchange.okcoin.service.polling.OkCoinTradeService;
import com.xeiam.xchange.currency.CurrencyPair;

public class OkCoinExchange extends BaseExchange {

  /**
   * The parameter name of the symbols that will focus on.
   */
  public static final String SYMBOLS_PARAMETER = "symbols";

  private static final List<CurrencyPair> SYMBOLS = Arrays.asList(CurrencyPair.BTC_CNY, CurrencyPair.LTC_CNY);

  @Override
  public void applySpecification(ExchangeSpecification exchangeSpecification) {

    super.applySpecification(exchangeSpecification);
    this.pollingMarketDataService = new OkCoinMarketDataService(exchangeSpecification);
    if (exchangeSpecification.getApiKey() != null) {
      this.pollingAccountService = new OkCoinAccountService(exchangeSpecification);
      this.pollingTradeService = new OkCoinTradeService(exchangeSpecification);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName()); // TODO: enable okcoin.com intl
    exchangeSpecification.setSslUri("https://www.okcoin.cn/api");
    exchangeSpecification.setHost("www.okcoin.cn");
    exchangeSpecification.setExchangeName("OKCoin");
    exchangeSpecification.setExchangeDescription("OKCoin is a globally oriented crypto-currency trading platform.");
    exchangeSpecification.setExchangeSpecificParametersItem(SYMBOLS_PARAMETER, SYMBOLS);
    return exchangeSpecification;
  }

}
