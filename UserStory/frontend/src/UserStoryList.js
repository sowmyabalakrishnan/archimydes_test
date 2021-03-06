import React, { Component } from 'react';
import './App.css';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
//import Home from './Home';
import { Link, withRouter} from 'react-router-dom';
import AppNavbar from './AppNavBar';
import { withCookies } from 'react-cookie';
import "react-table-6/react-table.css";
import axios from 'axios';

class UserStoryList extends Component {
 
  constructor(props) {
    super(props);
	this.state = {groups: [],  isLoading: true, user: (props.location.state?props.location.state.user:undefined), item: (props.location.state?props.location.state.item:undefined)};	
  }
  
  
  componentDidMount() {
	//if(this.props.location.state)
	//{
	//this.setState({user: this.props.location.state.user, item: this.props.location.state.item})
	//}
	let link = '';
	link=(this.props.location.state.item && this.props.location.state.item.roleId==true)?'/api/getAllStories/' : '/api/getStories/'+this.props.location.state.user.name;
	console.log("UserStoryList :: componentDidMount :: link -- "+link);
 	axios.get(link, {method: 'GET',headers:{'Accept': 'application/json','Content-Type': 'application/json'}})
	 .then(response => response.data)
     .then(data => this.setState({groups: data, isLoading: false}))
     .catch(() => this.props.history.push('*'));	 
  }


  render() {
    const {groups, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
	const groupList = groups ? groups.map(group => {      
      return <tr key={group.id} style={{background: ((group.status)&&group.status == "Rejected") ? 'red' : (((group.status)&&group.status == "Approved")?'green':'black'), color:'white'}}>
      <td style={{whiteSpace: 'nowrap'}}>{group.summary}</td>
        <td>{group.description}</td>
		 <td>{group.type}</td>
		 <td>{group.complexity}</td>
		 <td>{group.cost}</td>
		 <td>{group.eta}</td>
		 <td>{group.status}</td>
		 <td>{group.createdBy}</td>
		 <td><Button size="sm" color="primary"><Link to={{pathname: "/story/" + group.id, state: {user: this.state.user, item: this.state.item}}}>View</Link></Button></td>
        </tr>
    }) : {}
 const button = (this.state.item && this.state.item.roleId==true) ?
     <div/>
      :
 	<div className="float-right">
		<Button color="success"><Link to={{pathname: '/story/new', state: {user: this.state.user, item: this.state.item}}}>Add UserStories></Link></Button>
		</div>
      
    return (
      <div>
		<AppNavbar/>
		<Container fluid>	
			{button}			  
            <h2>User Stories</h2>
			 <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Summary</th>
              <th width="20%">Description</th>
              <th width="20%">Type</th>
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

export default withCookies(withRouter(UserStoryList));
