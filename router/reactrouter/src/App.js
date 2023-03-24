import React from "react";
import {
  createRoutesFromElements,
  createBrowserRouter,
  RouterProvider,
  Route,
} from "react-router-dom";
import "./App.css";
import Root, {
    loader as rootLoader,
    action as rootAction,
} from "./routes/root";
import ErrorPage from "./routes/error-page";
import Contact, {
    loader as contactLoader,
    action as contactAction,
} from "./routes/contact";
import EditContact, {
    action as editAction,
} from "./routes/edit";
import { action as destroyAction } from "./routes/destroy";
import Index from "./routes/index";

// const router = createBrowserRouter([
//   {
//     path: "/",
//     // element: <div>Hello world!</div>,
//     element: <Root />,
//     errorElement: <ErrorPage />,
//     loader: rootLoader,
//     action: rootAction,
//     children: [
//         {
//             errorElement: <ErrorPage/>,
//             children: [
//                 {index: true, element: <Index/>},
//                 {
//                     path: "contacts/:contactId",
//                     element: <Contact/>,
//                     loader: contactLoader,
//                     action: contactAction,
//                 },
//                 {
//                     path: "contacts/:contactId/edit",
//                     element: <EditContact/>,
//                     loader: contactLoader,
//                     action: editAction,
//                 },
//                 {
//                     path: "contacts/:contactId/destroy",
//                     action: destroyAction,
//                     errorElement: <div>Oops! There was an error.</div>,
//                 },
//             ],
//         },
//     ],
//   },
//   // {
//   //   path: "contacts/:contactId",
//   //   element: <Contact />,
//   // },
// ]);

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route
            path="/"
            element={<Root />}
            loader={rootLoader}
            action={rootAction}
            errorElement={<ErrorPage />}
        >
            <Route errorElement={<ErrorPage />}>
                <Route index element={<Index />} />
                <Route
                    path="contacts/:contactId"
                    element={<Contact />}
                    loader={contactLoader}
                    action={contactAction}
                />
                <Route
                    path="contacts/:contactId/edit"
                    element={<EditContact />}
                    loader={contactLoader}
                    action={editAction}
                />
                <Route
                    path="contacts/:contactId/destroy"
                    action={destroyAction}
                />
            </Route>
        </Route>
    )
);

function App() {
    return (
        <React.StrictMode>
            <RouterProvider router={router} />
        </React.StrictMode>
    );
}

export default App;