package com.example.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherApp extends JFrame {
    private JComboBox<String> locationSelector;
    private JLabel weatherInfo, weatherIcon;
    private JButton checkWeather;

    private final Map<String, String> weatherData;

    public WeatherApp() {
        weatherData = new LinkedHashMap<>();
        weatherData.put("Bandung", "Cerah, 29°C");
        weatherData.put("Yogyakarta", "Hujan, 26°C");
        weatherData.put("Medan", "Berawan, 27°C");

        setTitle("Weather Checker");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel title = new JLabel("Aplikasi Informasi Cuaca", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(title);

        JPanel locationPanel = new JPanel(new FlowLayout());
        locationSelector = new JComboBox<>(weatherData.keySet().toArray(new String[0]));
        locationPanel.add(new JLabel("Lokasi:"));
        locationPanel.add(locationSelector);
        topPanel.add(locationPanel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        weatherInfo = new JLabel("Pilih lokasi untuk melihat cuaca", JLabel.CENTER);
        weatherInfo.setFont(new Font("Serif", Font.ITALIC, 16));
        weatherIcon = new JLabel("", JLabel.CENTER);
        centerPanel.add(weatherInfo, BorderLayout.NORTH);
        centerPanel.add(weatherIcon, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        checkWeather = new JButton("Lihat Cuaca");
        checkWeather.setFont(new Font("SansSerif", Font.PLAIN, 14));
        bottomPanel.add(checkWeather);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        checkWeather.addActionListener(this::displayWeather);
    }

    private void displayWeather(ActionEvent e) {
        String location = (String) locationSelector.getSelectedItem();
        if (location != null && weatherData.containsKey(location)) {
            String info = weatherData.get(location);
            weatherInfo.setText("Cuaca di " + location + ": " + info);
            if (info.contains("Cerah")) {
                weatherIcon.setText("☀️");
            } else if (info.contains("Berawan")) {
                weatherIcon.setText("☁️");
            } else if (info.contains("Hujan")) {
                weatherIcon.setText("🌧️");
            }
        } else {
            weatherInfo.setText("Data cuaca tidak tersedia");
            weatherIcon.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherApp app = new WeatherApp();
            app.setVisible(true);
        });
    }
}
