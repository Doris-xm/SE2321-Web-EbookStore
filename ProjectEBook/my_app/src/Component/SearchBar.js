import React from 'react';
import '../css/View.css'
import { Button, Input, AutoComplete } from 'antd';
import {
    SearchOutlined
} from '@ant-design/icons'



const { Option } = AutoComplete;

function onSelect(value) {
    console.log('onSelect', value);
}



function searchResult(query) {

}

function renderOption(item) {

}

export class SearchBar extends React.Component {
    state = {
        dataSource: [],
    };

    handleSearch = value => {
        this.setState({
            dataSource: value ? searchResult(value) : [],
        });
    };

    render() {
        const { dataSource } = this.state;
        return (
            <div className="my-content" style={{ width: "50%"} }>
                <AutoComplete
                    className="global-search"
                    size="large"
                    style={{ width: '100%'  }}
                    // dataSource={dataSource.map(renderOption)}
                    onSelect={onSelect}
                    onSearch={this.handleSearch}
                    placeholder="搜索书名、作者"
                    optionLabelProp="text"
                >
                    <Input
                        suffix={
                            <Button
                                className="search-btn"
                                style={{ marginRight: -12 }}
                                size="large"
                                type="primary"
                            >
                                <SearchOutlined />
                            </Button>
                        }
                    />
                </AutoComplete>
            </div>
        );
    }
}