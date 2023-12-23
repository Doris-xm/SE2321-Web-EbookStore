import {getRequest} from "../util/ajax";

export const CountHotWords = async () => {
    const url = 'http://localhost:8010/hot_word';
    const data= null;
    let opts = {
        method: "GET",
        credentials: "include"
    };

    return fetch(url, opts)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log("getre:", data);
            return data;
        })
        .catch((error) => {
            console.log(error);
        });
};