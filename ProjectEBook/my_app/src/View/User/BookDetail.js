import React, {useEffect, useState} from "react";
import '../../css/View.css'
import {Badge, Button, Descriptions} from "antd";
import { useParams,useNavigate  } from "react-router-dom";
import {getBook} from "../../Service/BookService";
import {AddToCart} from "../../Service/CartService";
import {getUser} from "../../Service/UserService";

function BookDetail () {

    const {bookId} = useParams();
    const navigate = useNavigate();// 获取navigate对象
     const [book, setBook] = useState(null);
    useEffect(() => {
        const fetchData = async () => {
            const fetchedBook = await getBook(bookId);
            setBook(fetchedBook);
        };
        fetchData();
    }, [bookId]);


    if(!book) {
         return <div>Loading</div>;
    }
    const handleGoBack = () => {
        navigate(-1); // 调用navigate返回上一页
    };
    const handleAddToCart = () => {
        let user = getUser();
        if(user === null)
            navigate('/my/cart')
        AddToCart(bookId);
    };

    return (
            <div className="my-descriptions" >

                <Descriptions title="详细信息" bordered={false}  >
                    <Descriptions.Item label="" >
                        <img src={book.cover} style={{width:'200px'}}/>
                    </Descriptions.Item>
                    <Descriptions.Item label="书名">{book.title}</Descriptions.Item>
                    <Descriptions.Item label="作者" >{book.author}</Descriptions.Item>

                    <Descriptions.Item label="价格">{book.price}</Descriptions.Item>
                    <Descriptions.Item label="Order time">2018-04-24 18:00:00</Descriptions.Item>
                    <Descriptions.Item label="Usage Time" span={2}>
                        2019-04-24 18:00:00
                    </Descriptions.Item>
                    <Descriptions.Item label="Status" span={3}>
                        <Badge status="processing" text="Running" />
                    </Descriptions.Item>
                    <Descriptions.Item label="Negotiated Amount">$80.00</Descriptions.Item>
                    <Descriptions.Item label="Discount">$20.00</Descriptions.Item>
                    <Descriptions.Item label="Official Receipts">$60.00</Descriptions.Item>
                    <Descriptions.Item label="Config Info">
                        Data disk type: MongoDB
                        <br />
                        Database version: 3.4
                        <br />
                        Package: dds.mongo.mid
                        <br />
                        Storage space: 10 GB
                        <br />
                        Replication factor: 3
                        <br />
                        Region: East China 1
                        <br />
                    </Descriptions.Item>
                </Descriptions>
                <div className="button-group" style={{ display: 'flex',flexDirection:'row', justifyContent: 'space-between' }}>
                    <Button className="buttons" onClick={handleAddToCart}>
                        加入购物车
                    </Button>
                    <Button className="buttons" onClick={handleGoBack}>返回</Button>
                </div>

            </div>

        );

};
export default BookDetail;