export const checkUser = async (name, password) => {
    let user = null;
    try {
        const response = await fetch(`/user?name=${name}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        user = await response.json();
        console.log("checkUser", user);
    } catch (error) {
        console.error("Error fetching books:", error);
    }
    if(user === null)
        return 0;
    if(user.password === password) {
        const userJson = JSON.stringify(user);
        localStorage.setItem('User', userJson);
        return user.id;
    }
    return -1;
};

export const getUser = () => {
    const userJson = localStorage.getItem('User');
    const authToken = localStorage.getItem('authToken');
    console.log('authTOken',authToken)
    if (authToken) {
        return JSON.parse(userJson);
    }
    return null;
};
export const handleLogout = () => {
    localStorage.removeItem('authToken');
    localStorage.removeItem('User');
};