package codSoftJava;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterGUI {

    private JComboBox<String> baseCurrencyComboBox;
    private JComboBox<String> targetCurrencyComboBox;
    private JTextField amountField;
    private JLabel resultLabel;

    private Map<String, Double> exchangeRates;

    public CurrencyConverterGUI() {
        exchangeRates = createExchangeRates();

        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel baseCurrencyLabel = new JLabel("Base Currency:");
        baseCurrencyLabel.setBounds(20, 20, 150, 20);
        frame.add(baseCurrencyLabel);

        baseCurrencyComboBox = new JComboBox<>(getCurrencyArray());
        baseCurrencyComboBox.setBounds(180, 20, 150, 20);
        frame.add(baseCurrencyComboBox);

        JLabel targetCurrencyLabel = new JLabel("Target Currency:");
        targetCurrencyLabel.setBounds(20, 60, 150, 20);
        frame.add(targetCurrencyLabel);

        targetCurrencyComboBox = new JComboBox<>(getCurrencyArray());
        targetCurrencyComboBox.setBounds(180, 60, 150, 20);
        frame.add(targetCurrencyComboBox);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 100, 150, 20);
        frame.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(180, 100, 150, 20);
        frame.add(amountField);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(20, 140, 100, 30);
        frame.add(convertButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(20, 180, 350, 20);
        frame.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        frame.setVisible(true);
    }

    private String[] getCurrencyArray() {
        // Hardcoded currency array for simplicity
        return new String[]{"USD", "EUR", "GBP", "JPY", "CAD", "AUD"};
    }

    private Map<String, Double> createExchangeRates() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.85);
        rates.put("GBP", 0.73);
        rates.put("JPY", 113.14);
        rates.put("CAD", 1.27);
        rates.put("AUD", 1.36);
        return rates;
    }

    private void convertCurrency() {
        try {
            String baseCurrency = (String) baseCurrencyComboBox.getSelectedItem();
            String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            if (exchangeRates.containsKey(targetCurrency)) {
                double exchangeRate = exchangeRates.get(targetCurrency);
                double convertedAmount = amount * exchangeRate;
                resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, targetCurrency));
            } else {
                resultLabel.setText("Invalid target currency");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid amount. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverterGUI();
            }
        });
    }
}
