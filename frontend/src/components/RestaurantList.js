import React from 'react';


const RestaurantList = ({ restaurants }) => {

    const fallbackImageUrl = 'https://via.placeholder.com/150';
    return (
        <div className='w-full'>
            <h1 className='font-bold text-xl mb-4'>Top Restaurants</h1>
            <div className='flex flex-col h-[200px] overflow-y-auto'>
                {restaurants.length > 0 ? (
                    restaurants.map((restaurant) => (
                        <div key={restaurant.restaurantsId} className='flex flex-row justify-around p-2 gap-5 items-center mb-4 cursor-pointer'>
                            <div className='w-[20%]'>
                                <img 
                                    src={restaurant.heroImgUrl || fallbackImageUrl}
                                    alt={`${restaurant.name} logo`} 
                                    className='w-full h-auto object-cover rounded-md' 
                                />
                            </div>
                            <div className='w-[50%]'>
                                <p className='font-semibold'>{restaurant.name}</p>
                                <p className='text-sm text-gray-600'>{restaurant.establishmentTypeAndCuisineTags}</p>
                            </div>
                            <div className='w-[20%] text-center'>
                                <p className='font-semibold'>{restaurant.averageRating}</p>
                                <p className='text-sm text-gray-500'>Rating</p>
                            </div>
                        </div>
                    ))
                ) : (
                    <p className='text-gray-500'>No restaurants found.</p>
                )}
            </div>
        </div>
    );
}

export default RestaurantList;