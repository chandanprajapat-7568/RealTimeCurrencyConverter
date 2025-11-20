<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Currency Conversion Result</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 500px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .result-card {
            background-color: #e8f5e9;
            border: 1px solid #c8e6c9;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            text-align: center;
        }

        .amount {
            font-size: 2em;
            font-weight: bold;
            color: #2e7d32;
            margin: 10px 0;
        }

        .currency {
            font-size: 1.2em;
            color: #555;
        }

        .arrow {
            font-size: 1.5em;
            color: #4CAF50;
            margin: 10px 0;
        }

        .info {
            background-color: #f1f8e9;
            border: 1px solid #dcedc8;
            border-radius: 8px;
            padding: 15px;
            margin-top: 20px;
            font-size: 0.9em;
            color: #555;
        }

        .convert-again-btn {
            display: block;
            width: 100%;
            padding: 12px;
            background-color: #2196F3;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            font-size: 16px;
        }

        .convert-again-btn:hover {
            background-color: #1976D2;
        }

        .error {
            color: red;
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Currency Conversion Result</h1>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <c:if test="${empty error}">
            <div class="result-card">
                <div class="amount">${originalAmount} <span class="currency">${fromCurrency}</span></div>
                <div class="arrow">→</div>
                <div class="amount">${convertedAmount} <span class="currency">${toCurrency}</span></div>
            </div>
            
            <div class="info">
                <p><strong>Conversion Date:</strong> ${date}</p>
                <p><strong>From:</strong> ${fromCurrency}</p>
                <p><strong>To:</strong> ${toCurrency}</p>
            </div>
            
            <a href="index.jsp" class="convert-again-btn">Convert Again</a>
        </c:if>
    </div>
</body>
</html>