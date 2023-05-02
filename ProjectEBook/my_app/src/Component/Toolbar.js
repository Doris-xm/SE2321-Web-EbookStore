import React from 'react';

export class TooBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data: this.props.data,
            search: false,
            preSearchData: null,
        };
        this.handleSearch = this.handleSearch.bind(this);
        this.handleData = this.handleData.bind(this);
        this.handlePreSearchData = this.handlePreSearchData.bind(this);
    }
    handleSearch(x) {
        this.props.onSearch(x);
    }
    handleData(x) {
        this.props.onData(x);
    }
    handlePreSearchData(x) {
        this.props.onPreSearchData(x);
    }
    toggleSearch = () => {
        if (this.state.search) {
            this.handleData(this.state.preSearchData);
            this.handleSearch(false);
            this.handlePreSearchData(null);
        } else {
            this.handlePreSearchData(this.state.data);
            this.handleSearch(true);
        }
    };
    download(format, ev) {
        let contents = format === 'json'
            ? JSON.stringify(this.state.data)
            : this.state.data.reduce(function (result, row) {
                return result
                    + row.reduce(function (rowresult, cell, idx) {
                        return rowresult
                            + '"'
                            + cell.replace(/"/g, '""')
                            + '"'
                            + (idx < row.length - 1 ? ',' : '');
                    }, '')
                    + "\n";
            }, '');

        let URL = window.URL || window.webkitURL;
        let blob = new Blob([contents], {type: 'text/' + format});
        ev.target.href = URL.createObjectURL(blob);
        ev.target.download = 'data.' + format;
    };

    render = () => {
        const {data} = this.props;
        const {preSearchData} = this.props;
        const {search} = this.props;

        this.state.data = data;
        this.state.preSearchData = preSearchData;
        this.state.search = search;

        return (
            <div className="toolbar">
                <button onClick={this.toggleSearch}>Search</button>
                <a onClick={this.download.bind(this, 'json')}
                   href="data.json">Export JSON</a>
                <a onClick={this.download.bind(this, 'csv')}
                   href="data.csv">Export CSV</a>
            </div>
        );
    };
};

export default TooBar;