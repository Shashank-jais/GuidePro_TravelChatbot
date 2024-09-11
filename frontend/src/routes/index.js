import {createBrowserRouter} from 'react-router-dom'
import App from '../App'
import Home from '../pages/Home';
import Chat from '../pages/Chat';

const router = createBrowserRouter([
    {
        path: '/',
        element:<App/>,
        children:[
            {
                path:"",
                element:<Home/>
            },
            {
                path:"/chat",
                element:<Chat/>
            }
        ]
    }
])

export default router;