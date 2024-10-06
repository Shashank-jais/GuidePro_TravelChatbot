import React from 'react';
import { FaUtensils } from 'react-icons/fa'; 

const RestaurantList = ({ restaurants, Darkmode }) => {
    const fallbackImageUrl = 'https://imgs.search.brave.com/opj-a4kq8KwIi56rq3KKlHF25AXELMe-6qCqAnTmAek/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvNDc4/NDMyODI0L3Bob3Rv/L2Zhc2hpb24tc3R5/bGlzaC1yZXN0YXVy/YW50LWludGVyaW9y/LmpwZz9zPTYxMng2/MTImdz0wJms9MjAm/Yz15UVlkN3NLVFFT/dFgyZW9vZUZ4U3Vl/czUzYUJSXzlDR1dt/Y0toMTVMR2U4PQ';
    const iconStyle = 'w-full h-auto object-cover rounded-md'; 
    
    return (
        <>
            <h1 className={`font-bold text-xl ml-2 mb-4 ${Darkmode ? 'text-white' : 'text-gray-900'}`}>Top Restaurants</h1>
            <div className={`w-full ${Darkmode ? 'bg-gray-800 text-white' : 'bg-white text-gray-900'}`}>
                <div className={`flex flex-col h-[300px] overflow-y-auto ${Darkmode ? 'bg-gray-700' : 'bg-gray-100'}`}>
                    {restaurants.length > 0 ? (
                        restaurants.map((restaurant) => (
                            <div key={restaurant.restaurantsId} className={`flex flex-row justify-between p-2 gap-5 items-center mb-4 cursor-pointer ${Darkmode ? 'bg-gray-800' : 'bg-white'}`}>
                                <div className='w-[20%]'>
                                {restaurant.heroImgUrl ? (
                                        <img 
                                            src={restaurant.heroImgUrl}
                                            alt={`${restaurant.name} logo`} 
                                            className='w-full h-auto object-cover rounded-md' 
                                            onError={(e) => {
                                                e.target.onerror = null;
                                                e.target.src = fallbackImageUrl; // Replace with a fallback URL
                                            }}
                                        />
                                    ) : (
                                        <FaUtensils className={`${iconStyle} text-gray-500`} aria-label="Restaurant icon" />
                                    )}
                                </div>
                                <div className='w-[50%]'>
                                    <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>{restaurant.name}</p>
                                    <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>{restaurant.establishmentTypeAndCuisineTags.join(' , ')}</p>
                                </div>
                                <div className='w-[20%] text-center'>
                                    <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>{restaurant.averageRating}</p>
                                    <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-500'}`}>Rating</p>
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

export default RestaurantList;
