import React, { useEffect, useState } from 'react';
import ChatbotHeader from '../components/ChatbotHeader';
import { IoSendOutline } from "react-icons/io5";
import RestaurantList from '../components/RestaurantList'; // Ensure RestaurantList accepts props
import HotelList from '../components/HotelList';
import Weather from '../components/Weather';
import TouristPlaces from '../components/TouristPlaces';

const Chat = () => {
    const [Darkmode, setDark] = useState(true);
    const [inputMessage, setInputMessage] = useState('');
    const [messages, setMessages] = useState([]);
    const [restaurants, setRestaurants] = useState([]); // State to hold restaurant data
    const [hotels, setHotels] = useState([]);
    const [touristplaces,setTouristplaces] = useState([]);

    useEffect(() => {
        const dummyMessages = [
            // { id: 1, intent: "normal", sender: 'John Doe', content: 'Hello there!', timestamp: '10:30 AM', avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80' },
            // { id: 2, intent: "normal", sender: 'You', content: 'Hi John! How are you?', timestamp: '10:31 AM', avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80' },
            // { id: 3, intent: "normal", sender: 'John Doe', content: 'I\'m doing great, thanks for asking!', timestamp: '10:32 AM', avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80' },
            {
                id: 1,
                intent: "normal",
                sender: 'You',
                content: 'Hi, I’m looking for travel recommendations!',
                timestamp: '10:30 AM',
                avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80'
            },
            {
                id: 2,
                intent: "normal",
                sender: 'John Doe',
                content: 'Hello! Where are you planning to travel to?',
                timestamp: '10:31 AM',
                avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80'
            },
        ];
        setMessages(dummyMessages);
    }, []);

    const toDark = () => {
        setDark(!Darkmode);
    };

    const handleMessage = () => {
        if (inputMessage.trim() !== '') {
            const newMessage = {
                id: messages.length + 1, // Ensuring unique ID
                intent: "normal",
                sender: "You",
                content: inputMessage,
                avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',
                timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
            };

            // Functional update to append the new message to the existing ones
            setMessages(prevMessages => {
                // console.log('Previous messages:', prevMessages); // Log previous messages
                const updatedMessages = [...prevMessages, newMessage]; // Append the new message
                // console.log('Updated messages:', updatedMessages); // Log updated messages
                return updatedMessages;
            });

            setInputMessage('');

            fetchRestaurants(newMessage.content)
        }
    };



    const handleInputChange = (e) => {
        setInputMessage(e.target.value);
    };

    const fetchRestaurants = async (message) => {
        try {
            const response = await fetch(`http://localhost:8080/api/response?message=${message}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json(); // Parsing the JSON response
            // console.log(data); // Debug log for the fetched data

            // Check if the response contains 'intent'
            if (data && data.intent) {
                const newMessage = {
                    id: messages.length + 1,
                    intent: data.intent,
                    sender: "John Doe",
                    content: "",
                    avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',
                    timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
                };

                // Handle based on intent type
                switch (data.intent) {
                    case "Restaurantlist":
                        // Update the restaurants only if intent is "Restauranlist"
                        if (Array.isArray(data.restaurants)) {
                            newMessage.intent = data.intent;
                            newMessage.content = `Found ${data.restaurants.length} restaurants.`;
                            setMessages(prevMessages => [...prevMessages, newMessage]); // Update messages state
                            setRestaurants(data.restaurants); // Update restaurant data



                            // console.log("NewMessage:", newMessage);
                            // console.log("Restaurants:", restaurants);
                            // console.log("message:", messages);



                        } else {
                            newMessage.content = "No restaurants found.";
                            setMessages(prevMessages => [...prevMessages, newMessage]);
                        }
                        break;

                    case "Hotellist":
                        // Update the hotel only if intent is "hotellist"
                        if (Array.isArray(data.hotels)) {
                            newMessage.intent = data.intent;
                            newMessage.content = `Found ${data.hotels.length} hotels.`;
                            setMessages(prevMessages => [...prevMessages, newMessage]); // Update messages state
                            setHotels(data.hotels); // Update hotels data



                            // console.log("NewMessage:", newMessage);
                            // console.log("Restaurants:", restaurants);
                            // console.log("message:", messages);



                        } else {
                            newMessage.content = "No Hotel found.";
                            setMessages(prevMessages => [...prevMessages, newMessage]);
                        }
                        break;
                    case "Weather":
                        // Update the hotel only if intent is "hotellist"
                        console.log("NewMessage:", data);
                        newMessage.intent = data.intent;
                        newMessage.content = data.weather;
                        setMessages(prevMessages => [...prevMessages, newMessage]); // Update messages state



                        // console.log("NewMessage:", newMessage.content);
                        // console.log("Restaurants:", restaurants);
                        // console.log("message:", messages);




                        break;


                    case "TouristPlaces":
                        // Update the restaurants only if intent is "Restauranlist"
                        if (Array.isArray(data.places)) {
                            newMessage.intent = data.intent;
                            newMessage.content = `${data.places.length} Places To Visit In ${data.places[0].city}`;
                            setMessages(prevMessages => [...prevMessages, newMessage]); // Update messages state
                            setTouristplaces(data.places); // Update restaurant data


                            // console.log("NewMessage:", newMessage);
                            // console.log("Restaurants:", restaurants);
                            // console.log("message:", messages);



                        } else {
                            newMessage.content = "No Tourist Places found.";
                            setMessages(prevMessages => [...prevMessages, newMessage]);
                        }
                        break;
                    case "normal":
                        newMessage.intent = data.intent;
                        newMessage.content = data.message;
                        setMessages(prevMessages => [...prevMessages, newMessage]);
                        break;


                    default:
                        newMessage.content = "Unrecognized intent.";
                        setMessages(prevMessages => [...prevMessages, newMessage]);
                        console.log("Unknown intent: ", data.intent);
                }
            } else {
                console.log("No valid intent found in the response.");
            }
        } catch (error) {
            console.error('Error fetching restaurants:', error);
        }
    };


    // Function to render content based on intent
    const renderContentByIntent = (message) => {

        switch (message.intent) {
            case 'Restaurantlist':
                return <RestaurantList restaurants={restaurants} Darkmode={Darkmode} />;
            case 'Hotellist':
                return <HotelList hotels={hotels} Darkmode={Darkmode} />;
            case 'Weather':
                // console.log("Weather from main:  " , message);
                return <Weather weatherData={message.content} Darkmode={Darkmode} />;
            case 'TouristPlaces':
                    // console.log("Weather from main:  " , message);
                return <TouristPlaces places={touristplaces} Darkmode={Darkmode} />;
            default:
                return message.content;
        }
    };

















    return (
        <>
            <div className={`flex flex-col h-screen overflow-x-auto ${Darkmode ? 'bg-gray-900 text-white' : 'bg-gray-100 text-gray-900'}`}>
                <ChatbotHeader Darkmode={Darkmode} toDark={toDark} />

                <div className='flex-1 overflow-y-auto p-4'>
                    {messages.map((message) => (
                        <div key={message.id} className={`flex ${message.sender === 'You' ? 'justify-end' : 'justify-start'} mb-4`}>
                            <div className={`flex ${message.sender === 'You' ? 'flex-row-reverse' : 'flex-row'} items-end`}>
                                <img src={message.avatar} alt="avatar" className='w-8 h-8 rounded-full mr-2' />
                                <div className={`max-w-xl ${message.sender === 'You' ? 'bg-blue-500 text-white' : Darkmode ? 'bg-gray-700' : 'bg-white'} rounded-lg p-3 shadow`}>
                                    <p>
                                        {renderContentByIntent(message)}
                                    </p>
                                    <span className={`text-xs ${Darkmode ? 'text-gray-400' : 'text-white'} mt-1 block`}>{message.timestamp}</span>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>

                <div className={`p-3 pt-5 pb-5 ${Darkmode ? 'bg-gray-800' : 'bg-white'} flex items-center gap-6`}>
                    <input
                        type='text'
                        value={inputMessage}
                        onChange={handleInputChange}
                        placeholder='Type a message'
                        className={`flex-1 p-3 rounded-full ${Darkmode ? 'bg-gray-700 text-white' : 'bg-gray-200'} h-12 text-xl font-medium overflow-x-hidden`}
                    />
                    <button onClick={handleMessage} className='hover:text-blue-600'>
                        <IoSendOutline size={35} />
                    </button>
                </div>
            </div>
        </>
    );
};

export default Chat;
