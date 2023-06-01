import React from 'react';
import { Carousel } from 'antd';

export class BookCarousel extends React.Component{
    createContent = () => {
        const images = [
            require('../../asset/carousel/book1.jpg'),
            require('../../asset/carousel/book2.jpg'),
            require('../../asset/carousel/book3.jpg'),
            require('../../asset/carousel/book4.jpg'),
        ]
        let result = [];
        for (let i = 0; i < 4; i++) {
            let img = images[i];
            result.push(
            <div key={i}>
                <img alt={'error'+i}  src={img}/>
            </div>
            );
        }

        return result;
    };

    render = () => {
        const content = this.createContent();
        console.log("rendering");
        return (
            <Carousel autoplay >
                {content}
            </Carousel>
        );
    }

}



export default BookCarousel;
