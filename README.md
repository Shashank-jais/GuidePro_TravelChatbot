# GuidePro: Smart Travel Chatbot 🌍🤖

### Travel Smarter, Plan Better!  

GuidePro is your AI-powered travel assistant designed to make travel planning effortless and enjoyable. Whether you need recommendations for restaurants, weather forecasts, or personalized itineraries, GuidePro is here to help you navigate your journey with ease.  

🚀 **Empowered by cutting-edge NLP and APIs, GuidePro brings your travel plans to life in seconds!**  

---

## 🌟 Key Features  

- 🗺️ **Smart Recommendations**: Personalized suggestions for hotels, restaurants, and tourist attractions tailored to your preferences.  
- ☀️ **Real-Time Weather Updates**: Know the weather at your destination before you go.  
- 📅 **Automated Itineraries**: Let the bot craft a day full of adventure based on your inputs.  
- 🔗 **Seamless API Integrations**: Powered by trusted services like OpenWeather and Google Maps.  
- 💬 **Conversational Interaction**: Chat-friendly responses that feel intuitive and human-like.  

---

## 🏗️ System Architecture  

GuidePro combines a sleek **frontend**, a powerful **backend**, and advanced **NLP** for a seamless experience:  

### 🔵 **Frontend**  
An engaging and intuitive user interface, designed using **React** and styled with **Tailwind CSS**, ensures every interaction is smooth and responsive.  

![Frontend](https://github.com/Shashank-jais/GuidePro_TravelChatbot/blob/main/WhatsApp%20Image%202024-12-15%20at%2017.32.52_3fe8595b.jpg)  

---

### 🟢 **Backend**  
The heart of GuidePro, built with **Java Spring Boot**, handles NLP-based query processing and API integrations to fetch real-time data for your travel needs.  

![Backend](https://github.com/Shashank-jais/GuidePro_TravelChatbot/blob/main/WhatsApp%20Image%202024-11-28%20at%2017.30.07_92d38bda.jpg)  

---

### 🔴 **Chatbot Interface**  
A friendly, multilingual chatbot with emoji support, built for a truly interactive travel-planning experience.  

![Chatbot](https://github.com/Shashank-jais/GuidePro_TravelChatbot/blob/main/WhatsApp%20Image%202024-11-28%20at%2017.33.53_da0cb462.jpg)  

---

## 🛠️ Tech Stack  

| **Category**            | **Technology**                |  
|--------------------------|-------------------------------|  
| **Frontend**            | React, Tailwind CSS           |  
| **Backend**             | Java Spring Boot              |  
| **Database**            | MongoDB                       |  
| **Programming Languages** | Java, JavaScript             |  
| **APIs**                | RapidAPI                      |  

### Why This Stack?  
1. **Frontend**: React + Tailwind CSS ensures a visually appealing, responsive, and fast user interface.  
2. **Backend**: Spring Boot provides robust server-side processing for NLP and API integrations.  
3. **Database**: MongoDB’s flexibility handles dynamic travel data effectively.  
4. **APIs**: RapidAPI connects the system to live weather updates, hotel listings, and more.  

---

## 🧩 Module Overview  

Here’s a look under the hood to see how GuidePro works!  

### 📡 **Controller**  
- **`Home.java`**: The gateway between the frontend and backend.  
  - Processes queries received from the frontend.  
  - Uses NLP to understand the intent and triggers the appropriate service.  

### 🧠 **NLP Module**  
- Uses advanced language models to extract **intent** and **context** from user queries.  
- **Core Features**:  
  - **`Intent.java`**: Deciphers what the user wants.  
  - **`NER.java`**: Identifies named entities like city names and landmarks.  
  - **`Pipeline.java`**: Handles the complete flow of NLP processing.  
  - **`Tokenize.java`**: Breaks down sentences into meaningful components.  

### 🏢 **Model**  
- Data structures to manage and standardize travel data.  
  - **`HotelDTO.java`**: Stores hotel details.  
  - **`WeatherDTO.java`**: Captures weather information.  
  - **`TouristPlaceDTO.java`**: Holds data about tourist attractions.  

### ⚙️ **Service**  
- Integrates external APIs to fetch real-time data.  
  - **`WeatherLoc.java`**: Gets weather forecasts for user-specified locations.  
  - **`HotelsLoc.java`**: Finds hotels near a given location.  
  - **`RestaurantsLoc.java`**: Fetches top restaurants for food lovers.  

---

## 🔄 How It Works  

1. 🌐 **User Interaction**:  
   - Users interact with the chatbot on the frontend, entering queries like:  
     *"What’s the weather like in Goa?"*  
2. 🔍 **Backend Processing**:  
   - **`Home.java`** sends the query to the NLP module.  
   - NLP determines the user’s intent (*Weather Info*) and extracts details (*Goa*).  
3. 🌏 **Data Fetching**:  
   - The backend service calls external APIs via **RapidAPI** to fetch the required data.  
4. 📤 **Response Delivery**:  
   - The processed information is sent back to the frontend and displayed in a clean, user-friendly format.  

---

## 🎯 Target Use Cases  

- Planning a holiday with detailed itineraries and recommendations.  
- Finding local attractions, restaurants, and hotels at any destination.  
- Checking weather conditions before starting your journey.  

---

## 🌟 Why Choose GuidePro?  

1. **Speed**: Real-time data fetching ensures instant responses.  
2. **Personalization**: NLP tailors recommendations to your preferences.  
3. **Scalability**: Modular architecture can easily support additional features.  
4. **Ease of Use**: Designed for everyone—from travel enthusiasts to first-time planners!  

---

## 💬 Let's Talk!  

Got questions or suggestions? Reach out to us, and let's make GuidePro even better together!  

---
