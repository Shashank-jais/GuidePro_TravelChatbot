import React from 'react';
import { IoIosStar } from "react-icons/io";


const HotelList = ({ hotels, Darkmode }) => {
    return (<>
        <h1 className={`font-bold text-xl ml-2 mb-4 ${Darkmode ? 'text-white' : 'text-gray-900'}`}>Top Hotels</h1>
        <div className={`w-full ${Darkmode ? 'bg-gray-800 text-white' : 'bg-white text-gray-900'}`}>
            <div className={`flex flex-col h-[300px] overflow-y-auto ${Darkmode ? 'bg-gray-700' : 'bg-gray-100'}`}>
                {hotels.length > 0 ? (
                    hotels.map((hotel) => (
                        <div key={hotel.id} className={`flex flex-row justify-between p-2 gap-5 items-center mb-4 cursor-pointer ${Darkmode ? 'bg-gray-800' : 'bg-white'}`}>
                            <div className='w-[70%]'>
                                <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>{hotel.title}</p>
                                <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>{hotel.primaryInfo != "null" ? hotel.primaryInfo : 'No additional info'}</p>
                                <div className='flex items-center'>
                                    <p className={`text-sm pr-2 ${Darkmode ? 'text-gray-400' : 'text-gray-500'}`}>Rating: </p>
                                    <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>{hotel.rating} </p>
                                    <IoIosStar className={`text-yellow-500 ml-1`} aria-label="Star rating" />
                                </div>
                                <div className='flex items-center'>
                                    <p className={`text-sm pr-2 ${Darkmode ? 'text-gray-400' : 'text-gray-500'}`}>Price:</p>
                                    <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>
                                        {`${hotel.priceForDisplay}`}
                                    </p>
                                </div>
                            </div>

                            <div className='w-[30%] text-center'>


                                <div className='gradient-border'>
                                    <a
                                        href={hotel.externalUrl}
                                        target="_blank"
                                        rel="noopener noreferrer"
                                        className={`book-now-button text-sm`}
                                    >
                                        Book
                                    </a>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <p className={`text-gray-500 ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>No hotels found.</p>
                )}
            </div>
        </div>
    </>
    );
}

export default HotelList;


