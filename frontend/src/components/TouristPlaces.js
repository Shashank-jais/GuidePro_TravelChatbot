import React from 'react';

const TouristPlaces = ({ places, Darkmode }) => {
    return (
        <>
            <h1 className={`font-bold text-xl ml-2 mb-4 ${Darkmode ? 'text-white' : 'text-gray-900'}`}>{`${places.length} Places To Visit In ${places[0].city}`}</h1>
            <div className={`w-full ${Darkmode ? 'bg-gray-800 text-white' : 'bg-white text-gray-900'}`}>
                <div className={`flex flex-col h-[300px] overflow-y-auto ${Darkmode ? 'bg-gray-700' : 'bg-gray-100'}`}>
                    {places.length > 0 ? (
                        places.map((place, index) => (
                            <div key={place._id} className={`flex flex-row justify-between p-2 gap-5 items-center mb-4 cursor-pointer ${Darkmode ? 'bg-gray-800' : 'bg-white'}`}>

                                <div className='w-[90%]'>
                                    <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>{`${index + 1 + ". " + place.place.split(".")[1]}`}</p>
                                    <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'} text-justify`}>{place.place_desc}</p>
                                    <div className='flex gap-3 '>
                                        <p className={`text-sm  ${Darkmode ? 'text-white' : 'text-gray-900'} `}>City: </p>
                                        <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>{place.city}</p>
                                    </div>
                                    <div className='flex gap-3 '>
                                        <p className={`text-sm  ${Darkmode ? 'text-white' : 'text-gray-900'} `}>Rating: </p>
                                        <p className={`font-semibold ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>
                                            {isNaN(place.ratings) ? "4.2" : place.ratings}
                                        </p>
                                    </div>
                                </div>

                            </div>
                        ))
                    ) : (
                        <p className={`text-gray-500 ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>No restaurants found.</p>
                    )}
                </div>
            </div>
        </>
    );
};

export default TouristPlaces;
