# Real-Time Currency Converter

A Java web application that converts currencies using real-time exchange rates from the Frankfurter API.

## Features
- Convert one currency to another using real-time API data
- Clean and responsive user interface
- Input validation
- Support for major world currencies
- Works with both Tomcat and Glassfish servers

## Technology Stack
- Java Servlet API
- JSP (JavaServer Pages)
- Maven (Build tool)
- JSTL (JSP Standard Tag Library)
- JSON parsing
- HTML/CSS (Frontend)

## Prerequisites
- Java 8 or higher
- Apache Maven 3.6.0 or higher
- Apache Tomcat 8+ or Glassfish 5+
- Internet connection (to access currency API)

## How to Run
1. Clone this repository
2. Build the project using Maven: `mvn clean install`
3. Deploy the WAR file to your Tomcat's `webapps/` directory
4. Start Tomcat server
5. Access the application at `http://localhost:8080/RealTimeCurrencyConverter`

## API Used
- **Frankfurter API**: `https://api.frankfurter.app/latest`
- No API key required
- Provides real-time exchange rates for major currencies

## Supported Currencies
- USD (US Dollar)
- EUR (Euro)
- GBP (British Pound)
- JPY (Japanese Yen)
- INR (Indian Rupee)
- CAD (Canadian Dollar)
- AUD (Australian Dollar)
- CHF (Swiss Franc)
- CNY (Chinese Yuan)
- SEK (Swedish Krona)

## Project Structure
```
RealTimeCurrencyConverter/
├── README.md
├── pom.xml
├── Documentation/
│   ├── Project_Report_RealTime_Currency_Converter.txt
│   └── Screenshots/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/currency/controller/
│       │       └── CurrencyConverterServlet.java
│       └── webapp/
│           ├── index.jsp
│           ├── result.jsp
│           ├── style.css
│           └── WEB-INF/
│               └── web.xml
```

## Screenshots
![Home Page]([home page.png](https://github.com/chandanprajapat-7568/RealTimeCurrencyConverter/blob/master/home%20page.png))
![Result Page]([after run.png](https://github.com/chandanprajapat-7568/RealTimeCurrencyConverter/blob/master/after%20run.png))
 
## Endpoints
- `/` - Home page (index.jsp)
- `/convert` - Currency conversion servlet

## License
This project is licensed under the [MIT License](LICENSE).
