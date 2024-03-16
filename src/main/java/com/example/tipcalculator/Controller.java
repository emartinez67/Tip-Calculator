package com.example.tipcalculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.text.NumberFormat;

public class Controller {
    @FXML
    private TextField amountTextField;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private Label tipPercentageLabel;

    private double tipPercentage = 0.15;
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();


    public void initialize() {
        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                        tipPercentage = newValue.intValue() / 100.0;
                        tipPercentageLabel.setText(percent.format(tipPercentage));

                        double amount = Double.parseDouble(amountTextField.getText());
                        double tip = amount * tipPercentage;
                        double total = amount + tip;
                        tipTextField.setText(currency.format(tip));
                        totalTextField.setText(currency.format(total));
                    }
                }
        );
        amountTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                try {
                    double amount = Double.parseDouble(newValue);
                    double tip = amount * tipPercentage;
                    double total = amount + tip;

                    tipTextField.setText(currency.format(tip));
                    totalTextField.setText(currency.format(total));
                }
                catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public void handleCalculate() {
        double amount = Double.parseDouble(amountTextField.getText());
        double tip = amount * tipPercentage;
        double total = amount + tip;

        tipTextField.setText(currency.format(tip));
        totalTextField.setText(currency.format(total));

    }


}