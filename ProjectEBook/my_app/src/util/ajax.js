import { message } from "antd";

/*
* 发送FormData对象
* eg：表单数据、文件上传
* */
let postRequest_v2 = (url, data, callback) => {
    var formData = new FormData();

    for (let p in data) {
        console.log("here");
        console.log(p);
        console.log(data[p]);
        // if(data.hasOwnProperty(p))
        formData.append(p, data[p]);
    }

    // url = url + "?id=2"

    console.log("hh");
    console.log(data);
    console.log(formData.get("id"));

    let opts = {
        method: "POST",
        body: formData,
        credentials: "include"
    };

    fetch(url, opts)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
};

/*
* 传JSON对象
* */
let postRequest = (url, json, callback) => {
    let opts = {
        method: "POST",
        body: JSON.stringify(json),
        headers: {
            "Content-Type": "application/json",
        },
    };

    return fetch(url, opts)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            if(callback !== null)
                return callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
};

let postRequest_token = (url, json, callback) => {
    let opts = {
        method: "POST",
        body: JSON.stringify(json),
        headers: {
            "Content-Type": "application/json",
            "token": localStorage.getItem("token")
        },
    };

    return fetch(url, opts)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            if(callback !== null)
                return callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
};

export { postRequest, postRequest_v2,postRequest_token };
