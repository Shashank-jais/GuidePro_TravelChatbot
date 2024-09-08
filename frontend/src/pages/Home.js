import React from 'react';

const Home = () => {
  return (
    <div className='flex flex-col md:flex-row justify-around items-center text-white min-h-[86vh] w-full p-4'>
      <div className='p-6 pt-0 flex flex-col justify-between md:w-[48%]'>
        <div className='mb-4'>
          <p className='text-5xl md:text-6xl lg:text-8xl capitalize home-heading'>
            CHAT<br/>
            <span className='home-heading text-6xl md:text-8xl lg:text-10xl capitalize bg-gradient-to-r from-[#417ece] via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'>
              SMARTER
            </span>
          </p>
          <p className='font-bold text-lg md:text-xl lg:text-2xl'>
            Personalized Travel Assistant for Unforgettable Adventures
          </p>
        </div>
        <div>
          <button className='bg-gradient-to-r from-[#417ece] via-[#fbbed6] to-[#ca6fa5] text-white font-bold py-2 px-4 rounded-lg transition-transform transform hover:scale-105'>
            Get Started
          </button>
        </div>
      </div>
      <div className='border-2 border-red-800 p-6 md:w-[40%] flex justify-center items-center '>
        <p className='text-lg md:text-xl'>Image</p>
        {/* You can place an image here */}
      </div>
    </div>
  );
};

export default Home;
