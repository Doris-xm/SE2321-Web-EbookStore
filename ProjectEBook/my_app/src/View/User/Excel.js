import React from 'react';
import PropTypes from 'prop-types';
import '../../css/Book.css';
import Toolbar from "../../Component/Toolbar";
import Table from "../../Component/Table";

export class Excel extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data: this.props.initialData,
            sortby: null,
            descending: false,
            edit: null, // [row index, cell index],
            search: false,
            preSearchData: null,
        };
        this.handleSearch = this.handleSearch.bind(this);
        this.handleData = this.handleData.bind(this);
        this.handlePreSearchData = this.handlePreSearchData.bind(this);
        this.handleSortBy = this.handleSortBy.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleDescending = this.handleDescending.bind(this);
    }
    handleSearch(search) {
        this.setState((state, props) => ({search:search}));
    }
    handleData(data) {
        this.setState((state, props) => ({data:data}));
    }
    handlePreSearchData(preSearchData) {
        this.setState((state, props) => ({preSearchData:preSearchData}));
    }
    handleDescending(descending) {
        this.setState((state, props) => ({descending:descending}));
    }
    handleEdit(edit) {
        this.setState((state, props) => ({edit:edit}));
    }
    handleSortBy(sortby) {
        this.setState((state, props) => ({sortby:sortby}));
    }
    render = () => {
        return (
            <div style={{padding:'10px'}}>
                {/*{this.renderToolbar()}*/}
                <Toolbar data = {this.state.data} preSearchData = {this.state.preSearchData} search = {this.state.search}
                onSearch = {this.handleSearch} onData = {this.handleData} onPreSearchData = {this.handlePreSearchData}/>
                {/*{this.renderTable()}*/}
                <Table data = {this.state.data} preSearchData = {this.state.preSearchData} search = {this.state.search}
                       sortby = {this.state.sortby} descending = {this.state.descending} edit = {this.state.edit} headers = {this.props.headers}
                       onSearch = {this.handleSearch}
                       onData = {this.handleData}
                       onPreSearchData = {this.handlePreSearchData}
                       onEdit = {this.handleEdit}
                       onSortBy={this.handleSortBy}
                       onDescending = {this.handleDescending}  />
            </div>
        );
    };

};

Excel.propTypes = {
    headers: PropTypes.arrayOf(
        PropTypes.string
    ),
    initialData: PropTypes.arrayOf(
        PropTypes.arrayOf(
            PropTypes.string
        )
    ),
};

export default Excel;