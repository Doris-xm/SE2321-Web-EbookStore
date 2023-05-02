import './App.css';
import { createBrowserRouter, RouterProvider, } from "react-router-dom";
import Home from "./routes/home";
import ErrorPage from "./routes/error-page";
import React from "react";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    errorElement: <ErrorPage />,
  },
]);

const App: React.FC = () => {
  return (
      <RouterProvider router={router} />
  );
}

export default App;
