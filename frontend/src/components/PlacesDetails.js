import React from 'react';

const PlacesDetails = ({ details, Darkmode }) => {
    // Check if the details object exists and has the necessary properties
    if (!details || typeof details !== 'object') {
        return <p>No place details available</p>; // Fallback if data is missing
    }

    // Destructure the details object
    const {
        add = 'Unknown Address',
        lat = 'N/A',
        lon = 'N/A',
        link = '#',
        place_id = 'N/A',
    } = details;

    return (
        <div className={`w-full p-4 ${Darkmode ? 'bg-gray-800 text-white' : 'bg-white text-gray-900'}`}>
            <h1 className={`font-bold text-xl mb-4 ${Darkmode ? 'text-white' : 'text-gray-900'}`}>
                Place Details
            </h1>
            <div className={`flex flex-col min-h-[100px] overflow-y-auto ${Darkmode ? 'bg-gray-700' : 'bg-gray-100'}`}>
                <div className={`flex flex-col p-4 gap-2`}>
                    <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>
                        Address: {add}
                    </p>
                    <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>
                        Latitude: {lat}, Longitude: {lon}
                    </p>
                    <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>
                        Place ID: {place_id}
                    </p>
                    <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>
                        <a
                            href={link}
                            target="_blank"
                            rel="noopener noreferrer"
                            className={`underline ${Darkmode ? 'text-blue-400' : 'text-blue-600'}`}
                        >
                            View on Google Maps
                        </a>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default PlacesDetails;
