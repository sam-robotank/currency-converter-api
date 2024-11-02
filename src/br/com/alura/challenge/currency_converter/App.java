package br.com.alura.challenge.currency_converter;

import br.com.alura.challenge.currency_converter.controller.CurrencyConverterController;
import br.com.alura.challenge.currency_converter.model.CurrencyConverter;
import br.com.alura.challenge.currency_converter.view.CurrencyConverterView;

public class App {

  // app start method
  public static void main(String[] args) {

    // Currency Converter Model
    CurrencyConverter ccModel = new CurrencyConverter();

    // Currency Converter View
    CurrencyConverterView ccView = new CurrencyConverterView();

    // Currency Converter Controller
    CurrencyConverterController ccController =
        new CurrencyConverterController(ccView, ccModel);

    // Initializes the application
    ccController.start();
  }
}
