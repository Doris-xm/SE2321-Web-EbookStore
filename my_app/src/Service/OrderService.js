export const getOrders = async () => {
    let orders = [];
    try {
        const response = await fetch('/orders');
        const data = await response.json();
        orders = data;
        console.log("getorders", orders);
    } catch (error) {
        console.error("Error fetching orders:", error);
    }
    return orders;
};

