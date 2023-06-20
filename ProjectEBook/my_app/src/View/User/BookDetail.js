import React, {useEffect, useState} from "react";
import '../../css/View.css'
import {Badge, Button, Col, Descriptions, Layout, message, Row} from "antd";
import { useParams,useNavigate  } from "react-router-dom";
import {getBook} from "../../Service/BookService";
import {AddToCart} from "../../Service/CartService";
import {getUser} from "../../Service/UserService";
import {Content} from "antd/es/layout/layout";

function BookDetail () {

    const {bookId} = useParams();
    const navigate = useNavigate();// 获取navigate对象
     const [book, setBook] = useState(null);
    useEffect(() => {
        const fetchData = async () => {
            const fetchedBook = await getBook(bookId);
            console.log(fetchedBook)
            if(fetchedBook === null || fetchedBook.stocks < 0) {
                message.error("该书已下架");
                setBook(null);
                return;
            }
            setBook(fetchedBook);
        };
        fetchData();
    }, [bookId]);
    const handleGoBack = () => {
        navigate(-1); // 调用navigate返回上一页
    };

    if(!book) {
        return (
            <div>
                <div>该书已下架</div>
                <Button className="buttons" onClick={handleGoBack}>返回</Button>
            </div>
        );
    }

    const handleAddToCart = () => {
        let user = getUser();
        if(user === null)
            navigate('/my/cart')
        AddToCart(bookId);
    };

    return (
      <Layout style={{width:"80%",display: 'flex', alignItems: 'center',background:"transparent",marginTop:30,marginLeft:60}}>
            <Row>
                <Col span={6}>
                    <img src={book.cover} style={{ width: '200px' }} />
                </Col>
                <Col span={18}>
                    <Descriptions title="详细信息" bordered={false} style={{ fontWeight: 'bold' }}>
                        <Descriptions.Item label="书名" >{book.title}</Descriptions.Item>
                        <Descriptions.Item label="作者">{book.author}</Descriptions.Item>
                        <Descriptions.Item label="价格">{book.price}</Descriptions.Item>
                        <Descriptions.Item label="库存">
                            <Badge status="processing" text={book.stocks} />
                        </Descriptions.Item>
                        <Descriptions.Item label="ISBN编号">{book.isbn}</Descriptions.Item>
                        <Descriptions.Item label="销量">{book.sales}</Descriptions.Item>
                        <Descriptions.Item label="详细介绍">{book.introduce}</Descriptions.Item>
                    </Descriptions>
                    <div className="button-group" style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                        {book.stocks > 0 ? <Button className="buttons" onClick={handleAddToCart}>
                            加入购物车
                        </Button>:
                        <Button className="buttons" onClick={()=>{message.success("已通知店家补货");}}>
                            通知店家补货
                        </Button>}
                        <Button className="buttons" onClick={handleGoBack}>返回</Button>
                    </div>
                </Col>
            </Row>
        </Layout>
    );

};
export default BookDetail;