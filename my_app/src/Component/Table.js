import React from 'react';
import PropTypes from "prop-types";

export class Table extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: this.props.data,
            sortby: null,
            descending: false,
            edit: null, // [row index, cell index],
            search: false,
            preSearchData: null,
            // headers: this.props.headers,
        };
        this.handleSearch = this.handleSearch.bind(this);
        this.handleData = this.handleData.bind(this);
        this.handlePreSearchData = this.handlePreSearchData.bind(this);
        this.handleDescending = this.handleDescending.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleSortBy = this.handleSortBy.bind(this);
    }
    handleSortBy(x) {
        this.props.onSortBy(x);
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
    handleDescending(x) {
        this.props.onDescending(x);
    }
    handleEdit(x) {
        this.props.onEdit(x);
    }


    sort = (e) => {
        let column = e.target.cellIndex;
        let data = this.state.data.slice();
        let descending = this.state.sortby === column && !this.state.descending;
        data.sort(function (a, b) {
            return descending
                ? (a[column] < b[column] ? 1 : -1)
                : (a[column] > b[column] ? 1 : -1);
        });
        this.handleData(data);
        this.handleSortBy(column);
        this.handleDescending(descending);
    };

    showEditor = (e) => {
        this.handleEdit({
            row: parseInt(e.target.dataset.row, 10),
            cell: e.target.cellIndex,
        })
    };

    renderSearch = () =>  {
        if (!this.state.search) {
            return null;
        }
        return (
            <tr onChange={this.search}>
                {this.props.headers.map(function (ignore, idx) {
                    return <td key={idx}><input type="text" data-idx={idx}/></td>;
                })}
            </tr>
        );
    };

    search = (e) => {
        let needle = e.target.value.toLowerCase();
        if (!needle) {
            this.handleData(this.state.preSearchData);
            // this.setState({data: this.preSearchData});
            return;
        }
        let idx = e.target.dataset.idx;
        let searchdata = this.state.preSearchData.filter(function (row) {
            return row[idx].toString().toLowerCase().indexOf(needle) > -1;
        });
        this.handleData(searchdata);
    };

    save = (e) => {
        e.preventDefault();
        let input = e.target.firstChild;
        let data = this.state.data.slice();
        data[this.state.edit.row][this.state.edit.cell] = input.value;
        this.handleEdit(null);
        this.handleData(data);
    };

    render = () => {
        const {data} = this.props;
        const {preSearchData} = this.props;
        const {search} = this.props;
        const {edit} = this.props;
        const {sortby} = this.props;
        const {descending} = this.props;

        this.state.data = data;
        this.state.preSearchData = preSearchData;
        this.state.search = search;
        this.state.edit = edit;
        this.state.sortby = sortby;
        this.state.descending = descending;

        return (
            <table>
                <thead onClick={this.sort}>
                <tr>{
                    this.props.headers.map(function (title, idx) {
                        if (this.state.sortby === idx) {
                            title += this.state.descending ? ' \u2191' : ' \u2193';
                        }
                        return <th key={idx}>{title}</th>;
                    }, this)
                }</tr>
                </thead>
                <tbody onDoubleClick={this.showEditor}>
                {this.renderSearch()}
                {this.state.data.map(function (row, rowidx) {
                    return (
                        <tr key={rowidx}>{
                            row.map(function (cell, idx) {
                                let content = cell;
                                let edit = this.state.edit;
                                if (edit && edit.row === rowidx && edit.cell === idx) {
                                    content = (
                                        <form onSubmit={this.save}>
                                            <input type="text" defaultValue={cell}/>
                                        </form>
                                    );
                                }
                                return <td key={idx} data-row={rowidx}>{content}</td>;
                            }, this)}
                        </tr>
                    );
                }, this)}
                </tbody>
            </table>
        );
    }
};

Table.propTypes = {
    headers: PropTypes.arrayOf(
        PropTypes.string
    )
};

export default Table;