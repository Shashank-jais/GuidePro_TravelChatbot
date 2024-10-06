import React from 'react';

const Weather = ({ weatherData, Darkmode }) => {
    // Check if weatherData exists and has the necessary properties
    if (!weatherData || typeof weatherData !== 'object') {
        return <p>No weather data available</p>; // Fallback if data is missing
    }

    // Destructure the weatherData object
    const {
        location_name = 'Unknown Location',
        region = 'Unknown Region',
        country = 'Unknown Country',
        condition_text = 'No condition information',
        temp_c = 'N/A',
        feelslike_c = 'N/A',
        humidity = 'N/A',
        wind_dir = 'N/A',
        wind_kph = 'N/A',
        vis_km = 'N/A',
        precip_mm = 'N/A',
        uv = 'N/A',
        pm2_5 = 0,
        air_quality_status = 'unknown',
        time_of_day = 'N/A', // Added to handle time_of_day
        activity_suggestion = 'unknown', // Added to handle activity_suggestion
    } = weatherData;

    const airQualityStatus = pm2_5 > 100 ? 'poor' : 'moderate'; // Adjusting this based on PM2.5
    const timeOfDay = uv > 2 ? 'day' : 'evening';
    const activitySuggestion = airQualityStatus === 'poor' ? 'indoors' : 'outdoors';

    return (
        <>
            <h1 className={`font-bold text-xl ml-2 mb-4 ${Darkmode ? 'text-white' : 'text-gray-900'}`}>
                Weather Information :
            </h1>
            <div className={`w-full ${Darkmode ? 'bg-gray-800 text-white' : 'bg-white text-gray-900'}`}>
                <div className={`flex flex-col min-h-[100px] overflow-y-auto ${Darkmode ? 'bg-gray-700' : 'bg-gray-100'}`}>
                    <div className={`flex flex-col p-4 gap-2 `}>
                        <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>
                            The current weather in {location_name}, {region}, {country} is {condition_text} with a temperature of {temp_c}°C, but it feels like {feelslike_c}°C.
                        </p>
                        <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>
                            The humidity level is {humidity}%, and the wind is coming from the {wind_dir} at a speed of {wind_kph} km/h.
                        </p>
                        <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray-600'}`}>
                            The air quality is {airQualityStatus}, with a PM2.5 level of {pm2_5}, which may affect sensitive individuals.
                        </p>
                        <p className={`text-sm ${Darkmode ? 'text-gray-400' : 'text-gray600'}`}>
                            The visibility is {vis_km} km with {precip_mm} mm of precipitation, and the UV index is {uv}.
                        </p>
                        <p className={`font-semibold ${Darkmode ? 'text-white' : 'text-gray-900'}`}>
                            Overall, it's a {timeOfDay} to be {activitySuggestion}!
                        </p>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Weather;
