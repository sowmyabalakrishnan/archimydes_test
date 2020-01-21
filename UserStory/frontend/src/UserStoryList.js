import React, { Component } from 'react';
import './App.css';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch, withRouter} from 'react-router-dom';
import { Link } from 'react-router-dom';
import {connect} from 'react-redux';
//import { mapDispatchToProps } from 'redux';
//import {loadUserStoryInfo} from '../../actions/new.js';

class UserStoryList extends Component {
  state = {
    isLoading:true,
    groups: []
  };
  
  
  async componentDidMount() {
	  console.log('0');
	  var test = [];
    const response = await fetch('/api/getStories'
	,{
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }})
	console.log('1',test);
    const body = await response.json();//await test;//
	//[{"id":1,"createdBy":"Sowmya","summary":"Summary summary Test summary","description":"Test description","type":"Change Request","complexity":"Medium","eta":"27-Jan-2019","cost":50,"status":"New"}];
	console.log('body',body);
    this.setState({ groups:body, isLoading:false });
  }

  render() {
    const {groups, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
	
	const onSelectRow=function (group) {
              this.props.history.push({
                pathname: '/userstories/:id',
                 state: {
                    key: group.id
                } 
            });  
        }
		
		 const onLogout=function (group) {
              this.props.history.push({
                pathname: '/userstory/' + group.id,
                 state: {
                    key: group
                } 
            });  
        }
	
	const groupList = groups.map(group => {      
      return <tr key={group.id}>
      <td style={{whiteSpace: 'nowrap'}}>{group.summary}</td>
        <td>{group.description}</td>
		 <td>{group.type}</td>
		 <td>{group.complexity}</td>
		 <td>{group.cost}</td>
		 <td>{group.eta}</td>
		 <td>{group.status}</td>
		 <td>{group.createdBy}</td>
		 <td><Button size="sm" color="primary" tag={Link} to={"/userstory/" + group.id}>View</Button></td>
        </tr>
    });

    return (
      <div>
<Container fluid>	  
		<div className="float-right">
		<Button color="success" tag={Link} to="/userstory/">Add UserStories</Button>
		</div>	  
            <h2>User Stories</h2>
			 <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Summary</th>
              <th width="20%">Description</th>
              <th>Type</th>
              <th width="10%">Complexity</th>
			  <th width="10%">Cost</th>
			  <th width="10%">ETA</th>
			  <th width="10%">Status</th>
			  <th width="10%">Created By</th>
            </tr>
            </thead>
            <tbody>
            {groupList}
            </tbody>
          </Table> 
</Container>		  
      </div>
    );
  }
}

export default withRouter(UserStoryList);
