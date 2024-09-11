import React, { useState } from 'react'
import { IoMenu } from "react-icons/io5";
import { FiSun, FiMoon } from "react-icons/fi";
import { Link } from 'react-router-dom';


const ChatbotHeader = ({Darkmode,toDark}) => {
    
    return (
      
            <header className={` p-3  w-full flex justify-between items-center bg-gradient border-r-0 `}>

               <Link to={'/'}> <h1 className=' cursor-pointer text-4xl  font-extrabold text-black'>
                    GuidePro
                </h1>
                </Link>

                <button
                    onClick={toDark}
                    className={`p-2 rounded-full   ${Darkmode ? 'bg-gray-700 text-yellow-400' : 'bg-gray-200 text-gray-600'}`}
                    aria-label={Darkmode ? 'Switch to light mode' : 'Switch to dark mode'}
                >
                    {Darkmode ? <FiSun size={24} /> : <FiMoon size={24}/>}
                </button>
            </header>

    )
}

export default ChatbotHeader
