package com.currency.controller;

import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class CurrencyConverterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String API_URL = "https://api.frankfurter.app/latest";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Retrieve form parameters
            String amountStr = request.getParameter("amount");
            String fromCurrency = request.getParameter("fromCurrency");
            String toCurrency = request.getParameter("toCurrency");

            // Validate input
            if (amountStr == null || amountStr.trim().isEmpty() || 
                fromCurrency == null || fromCurrency.trim().isEmpty() ||
                toCurrency == null || toCurrency.trim().isEmpty()) {
                
                request.setAttribute("error", "All fields are required!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Validate numeric amount
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    request.setAttribute("error", "Amount must be a positive number!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Amount must be a valid number!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Call the currency conversion API
            String apiUrl = API_URL + "?amount=" + amount + "&from=" + fromCurrency + "&to=" + toCurrency;
            
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000); // 5 seconds
            connection.setReadTimeout(5000); // 5 seconds

            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                
                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(content.toString());
                
                // Extract data from JSON
                String date = jsonResponse.getString("date");
                JSONObject rates = jsonResponse.getJSONObject("rates");
                double convertedAmount = rates.getDouble(toCurrency);
                
                // Format the amounts for display
                DecimalFormat df = new DecimalFormat("#.####");
                
                // Set attributes for the result page
                request.setAttribute("originalAmount", df.format(amount));
                request.setAttribute("fromCurrency", fromCurrency);
                request.setAttribute("convertedAmount", df.format(convertedAmount));
                request.setAttribute("toCurrency", toCurrency);
                request.setAttribute("date", date);
                
                // Forward to result page
                RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
                dispatcher.forward(request, response);
                
            } else {
                request.setAttribute("error", "Error calling currency conversion API. Please try again later.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            
            connection.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during conversion: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests to index.jsp
        response.sendRedirect("index.jsp");
    }
}