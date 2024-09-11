import React, { useState } from 'react'
import { IoMenu } from "react-icons/io5";
import { Link } from 'react-router-dom';

const Header = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  const toggleMobileMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  return (
    <div className='text-white flex m-3 p-2 '>
      <div className='w-[90%] md:w-[40%] flex md:justify-center items-center  ml-[15px] md:ml-0'>
        <h1 className=' text-4xl md:text-5xl font-extrabold bg-gradient-to-r from-[#417ece]  via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text  '>
          GuidePro
        </h1>
      </div>


      <div className='hidden md:w-[60%] md:flex md:justify-evenly md:items-center gap-1 ml-2'>
        {[
          { name: 'Home', path: '/' },
          { name: 'About', path: '/about' },
          { name: 'Features', path: '/feature' }
        ].map((item) => (
          <Link
            key={item.name}
            to={item.path}
            className="cursor-pointer bg-gradient-to-r from-[#417ece] via-[#fbbed6] to-[#ca6fa5] text-transparent bg-clip-text border-2 border-transparent hover:border-white rounded-full p-2 transition duration-300 ease-in-out text-lg font-bold"
            aria-label={item.name}
          >
            {item.name}
          </Link>
        ))}
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
