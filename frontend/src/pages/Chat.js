import React, { useEffect, useState } from 'react'
import ChatbotHeader from '../components/ChatbotHeader'
import { IoSendOutline } from "react-icons/io5";

const Chat = () => {
    const [Darkmode, setDark] = useState(false);
    const [inputMessage, setInputMessage] = useState('');
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        // Simulate receiving messages
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

    const handlemessage = () => {
        if (inputMessage.trim() !== '') {
            const newMessage = {
                id: messages.length + 1,  // Use length of messages
                sender: "You",
                content: inputMessage,
                avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',
                timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
            };
            setMessages([...messages, newMessage]); // Update messages state
            setInputMessage(''); // Reset input field
        }
    };

    const handleInputChange = (e) => {
        setInputMessage(e.target.value);
    };

    return (
        <>
            <div className={`flex flex-col h-screen overflow-x-auto  ${Darkmode ? 'bg-gray-900 text-white' : 'bg-gray-100 text-gray-900'}`}>
                <ChatbotHeader Darkmode={Darkmode} toDark={toDark} />

                <div className='flex-1 overflow-y-auto p-4'>
                    {messages.map((message) => (
                        <div key={message.id} className={`flex ${message.sender === 'You' ? 'justify-end' : 'justify-start'} mb-4`}>
                            <div className={`flex ${message.sender === 'You' ? 'flex-row-reverse' : 'flex-row'} items-end`}>
                                <img src={message.avatar} alt="avatar" className='w-8 h-8 rounded-full mr-2' />
                                <div className={`max-w-xs ${message.sender === 'You' ? 'bg-blue-500 text-white' : Darkmode ? 'bg-gray-700' : 'bg-white'} rounded-lg p-3 shadow`}>
                                    <p>{message.content}</p>
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
                    <button onClick={handlemessage} className='hover:text-blue-600'>
                        <IoSendOutline size={35} />
                    </button>
                </div>
            </div>
        </>
    );
};

export default Chat;
