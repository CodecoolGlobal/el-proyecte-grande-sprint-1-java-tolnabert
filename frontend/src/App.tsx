import {RouterProvider, createBrowserRouter} from "react-router-dom";

import Landing from "./pages/Landing";
import HomeLayout from "./pages/HomeLayout";
import Recipes from "./pages/Recipes";
import AboutUs from "./pages/AboutUs";
import FavoriteRecipes from "./pages/FavoriteRecipes";
import ShoppingList from "./pages/ShoppingList";
import MyRecipes from "./pages/MyRecipes";
import Login from "./pages/Login";
import Register from "./pages/Register";
import CreateRecipe from "./pages/CreateRecipe";
import SingleRecipe from "./pages/SingleRecipe.tsx";
import Profile from "./pages/Profile";
import ChangeProfile from "./pages/ChangeProfile";

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomeLayout />,
    children: [
      {
        index: true,
        element: <Landing />,
      },
      {
        path: "/about-us",
        element: <AboutUs />,
      },
      {
        path: "/recipes",
        element: <Recipes />,
      },
      {
        path: "/favorite-recipes",
        element: <FavoriteRecipes />,
      },
      {
        path: "/my-recipes",
        element: <MyRecipes />,
      },
      {
        path: "/create-recipe",
        element: <CreateRecipe />,
      },
      {
        path: "/shopping-list",
        element: <ShoppingList />,
      },
      {
        path: "/my-profile",
        element: <Profile />,
      },
      {
        path: "/change-profile",
        element: <ChangeProfile />,
      },
      {
        path: "/single-recipe/:id",
        element: <SingleRecipe/>
      }
    ],
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Register />,
  },
]);

function App() {
    return <RouterProvider router={router}/>;
}

export default App;
