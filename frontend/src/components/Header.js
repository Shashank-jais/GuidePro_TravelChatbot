import React, { useState } from 'react'
import { IoMenu } from "react-icons/io5";

const Header = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  const toggleMobileMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  return (
    <div className='text-white flex m-3 p-2 '>
      <div className='w-[90%] md:w-[40%] flex justify-center items-center'>
        <h1 className=' text-3xl md:text-5xl font-extrabold bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>
          GuidePro
        </h1>
      </div>


      <div className='hidden md:w-[60%] md:flex md:justify-center md:items-center'>
        <ul className='flex justify-evenly items-center w-full text-xl font-bold'>
          <li className='bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>Home</li>
          <li className='bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>About</li>
          <li className='bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>Features</li>
        </ul>
      </div>

      {/* Mobile Menu */}
      <div className='w-[10%] md:hidden flex justify-center items-center text-4xl'>
        <IoMenu onClick={toggleMobileMenu} />
      </div>
      <div
        className={`fixed top-0 right-0 bg-slate-900 text-white w-[75%] h-full flex flex-col  pt-24  transition-transform duration-300 ${isMobileMenuOpen ? 'translate-x-0' : 'translate-x-full'}`}
        style={{ transform: isMobileMenuOpen ? 'translateX(0)' : 'translateX(100%)' }}
      >
        <button className='absolute top-4 right-4 text-3xl' onClick={toggleMobileMenu}>✕</button>
        <ul className='flex flex-col justify-around items-start pl-10 font-bold text-2xl h-2/4'>
          <li className='bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>Home</li>
          <li className='bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>About</li>
          <li className='bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>Features</li>
        </ul>
      </div>
    </div>
  )
}

export default Header
