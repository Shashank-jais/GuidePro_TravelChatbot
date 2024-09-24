import React, { useEffect, useState } from 'react';
import ChatbotHeader from '../components/ChatbotHeader';
import { IoSendOutline } from "react-icons/io5";
import RestaurantList from '../components/RestaurantList'; // Ensure RestaurantList accepts props

const Chat = () => {
    const [Darkmode, setDark] = useState(false);
    const [inputMessage, setInputMessage] = useState('');
    const [messages, setMessages] = useState([]);
    const [restaurants, setRestaurants] = useState([]); // State to hold restaurant data
    const [loading, setLoading] = useState(false); // Loading state

    useEffect(() => {
        const dummyMessages = [
            { id: 1, sender: 'John Doe', content: 'Hello there!', timestamp: '10:30 AM', avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80' },
            { id: 2, sender: 'You', content: 'Hi John! How are you?', timestamp: '10:31 AM', avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80' },
            { id: 3, sender: 'John Doe', content: 'I\'m doing great, thanks for asking!', timestamp: '10:32 AM', avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80' },
        ];
        setMessages(dummyMessages);
    }, []);

    const toDark = () => {
        setDark(!Darkmode);
    };

    const handleMessage = () => {
        if (inputMessage.trim() !== '') {
            const newMessage = {
                id: messages.length + 1,
                sender: "You",
                content: inputMessage,
                avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',
                timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
            };
            setMessages([...messages, newMessage]);
            setInputMessage('');
            fetchRestaurants(newMessage.content); 
        }
    };

    const handleInputChange = (e) => {
        setInputMessage(e.target.value);
    };

    const fetchRestaurants = async (locationId) => {
        setLoading(true); // Set loading to true
        try {
            const response = await fetch(`http://localhost:8080/api/restaurants?locationId=${locationId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            console.log(response);
            if (data && Array.isArray(data)) {
                console.log(data);
                setRestaurants(data); // Set fetched restaurant data to state
            } else {
                console.log("Unexpected data format: ", data);
            }
        } catch (error) {
            console.error('Error fetching restaurants:', error);
        } finally {
            setLoading(false); // Set loading to false
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
                                        {message.content ? (
                                            message.content
                                        ) : (
                                            loading ? (
                                                <span>Loading restaurants...</span> // Loading state message
                                            ) : (
                                                <RestaurantList restaurants={restaurants} />
                                            )
                                        )}
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
