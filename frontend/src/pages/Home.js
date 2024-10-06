import React, { useEffect, useState } from 'react';
import updated from '../images/updated.mp4';
import Header from '../components/Header';
import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';

const Home = () => {
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    setLoaded(true);
  }, []);

  const headingText = "CHAT".split(" ");
  const subHeadingText = "Personalized Travel Assistant for Unforgettable Adventures".split(" ");
  const smarterText = "SMARTER";

  return (
    <>
      <Header />
      <div className='flex flex-col md:flex-row justify-around items-center text-white min-h-[86vh] w-full p-4'>
        <div className={`md:ml-12 p-6 pt-10 flex flex-col justify-between h-full md:w-[48%]`}>
          <div className='mb-4'>
            <p className='text-5xl md:text-6xl lg:text-8xl capitalize home-heading'>
              {headingText.map((el, i) => (
                <motion.span
                  initial={{ opacity: 0 }}
                  animate={{ opacity: 1 }}
                  transition={{
                    duration: 0.25,
                    delay: i / 10
                  }}
                  key={i}
                >
                  {el}{" "}
                </motion.span>
              ))}
              <br />
              <motion.span 
                className='home-heading text-6xl md:text-8xl lg:text-10xl capitalize bg-gradient-to-r from-[#417ece] via-[#fbbed6] to-[#ca6fa5] inline-block text-transparent bg-clip-text'
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{
                  duration: 0.25,
                  delay: headingText.length / 10 // Delay to follow the previous text
                }}
              >
                {smarterText}
              </motion.span>
            </p>
            <p className='font-bold text-lg md:text-xl lg:text-2xl'>
              {subHeadingText.map((el, i) => (
                <motion.span
                  initial={{ opacity: 0 }}
                  animate={{ opacity: 1 }}
                  transition={{
                    duration: 0.25,
                    delay: (headingText.length + 1 + i) / 10 // Adjust delay for subheading
                  }}
                  key={i}
                >
                  {el}{" "}
                </motion.span>
              ))}
            </p>
          </div>
          <div>
            <Link to={'/chat'}>
              <button className='bg-gradient-to-r from-[#417ece] via-[#fbbed6] to-[#ca6fa5] text-white font-bold py-2 px-4 rounded-lg transition-transform transform hover:scale-105'>
                Get Started
              </button>
            </Link>
          </div>
        </div>
        <div className='h-[350px] md:w-[35%] md:h-[35%] flex justify-center items-center rounded-full overflow-hidden'>
          <video className='w-full h-full object-cover ' autoPlay muted loop>
            <source src={updated} type="video/mp4" />
            Your browser does not support the video tag.
          </video>
        </div>
      </div>
    </>
  );
};

export default Home;
