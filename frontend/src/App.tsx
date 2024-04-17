import { RouterProvider, createBrowserRouter } from "react-router-dom";

import Landing from "./pages/Landing";
import HomeLayout from "./pages/HomeLayout";
import Recipes from "./pages/Recipes";
import AboutUs from "./pages/AboutUs";
import FavoriteRecipes from "./pages/FavoriteRecipes";
import ShoppingList from "./pages/ShoppingList";
import SelectedRecipes from "./pages/SelectedRecipes";
import Login from "./pages/Login";
import Register from "./pages/Register";

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
        path: "/selected-recipes",
        element: <SelectedRecipes />,
      },
      {
        path: "/shopping-list",
        element: <ShoppingList />,
      },
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
  return <RouterProvider router={router} />;
}

export default App;
