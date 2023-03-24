import './App.css';
import { createBrowserRouter, RouterProvider, } from "react-router-dom";
import Home from "./routes/Home";
import ErrorPage from "./error-page.jsx";
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
